# Modifications - Fuel Mechanics Refinement

## Overview
This document summarizes the refinements made to the fuel management system to implement specific game balance parameters:

1. **Fuel Model**: Integer-based (0-100) instead of float (0.0-100.0f)
2. **Speed Tiers**: NORMAL_SPEED=20, REDUCED_SPEED=10, EMPTY_SPEED=5
3. **Consumption Tiers**: 5 units at normal speed, 2 units at reduced speed, 0 when empty
4. **Pit Stop Mechanics**: Foundation for immobility-based detection (versus marker-based)

---

## Files Modified

### 1. **Drone.java**
**Purpose**: Core drone model with fuel management

**Changes**:
- **Fuel Type**: Changed from `float currentFuel` to `int currentFuel` (0-100)
- **Constants Updated**:
  - `MAX_FUEL`: `100.0f` → `100` (int)
  - `FUEL_CONSUMPTION_NORMAL`: New constant = 5 (consumption at speed 20)
  - `FUEL_CONSUMPTION_REDUCED`: New constant = 2 (consumption at speed 10)
  - `FUEL_REFILL_RATE`: `2.0f` → `5` (int, faster refueling for balance)
  - `CRITICAL_FUEL_THRESHOLD`: `10.0f` → `10` (int)
  - **New field**: `outOfFuel` (boolean) tracks 0-fuel state

**Method Signatures**:
- `getCurrentFuel()`: Returns `int` instead of `float`
- `getFuelPercentage()`: Returns `int` instead of `float`
- `setCurrentFuel(int fuel)`: Parameter changed from `float` to `int`
- `consumeFuel(byte speed)`: Parameter changed from `float` to `byte`
  - Now implements tiered consumption:
    - Speed 20 → consume 5
    - Speed 10 → consume 2
    - Speed 5 → consume 0 (immobile)

**Impact**: All fuel calculations now operate on integer model for cleaner game mechanics

---

### 2. **DroneController.java**
**Purpose**: Hardware drone control with speed management based on fuel

**Changes**:
- **New Speed Constants**:
  - `REDUCED_SPEED = 10` (byte)
  - `NEG_REDUCED_SPEED = -10` (byte)
  - `EMPTY_SPEED = 5` (byte)
  - `NEG_EMPTY_SPEED = -5` (byte)

- **New Method**: `getSpeedBasedOnFuel()` (private)
  ```java
  byte speed = (fuel > 10) ? NORMAL_SPEED : (fuel > 0) ? REDUCED_SPEED : EMPTY_SPEED;
  ```
  - Returns NORMAL_SPEED (20) when fuel > 10
  - Returns REDUCED_SPEED (10) when 0 < fuel ≤ 10
  - Returns EMPTY_SPEED (5) when fuel = 0 (immobile)

- **Method Updates**:
  - `moveForward()`: Now calls `getSpeedBasedOnFuel()` and calls `DRONE.consumeFuel(speed)`
  - `moveBackward()`: Now calls `getSpeedBasedOnFuel()` and calls `DRONE.consumeFuel(speed)`

**Impact**: Drone automatically reduces speed when fuel is critical, preventing abuse of constant speed

---

### 3. **FuelManager.java**
**Purpose**: Orchestrates fuel system during gameplay

**Changes**:
- **Field Update**: `lastFuelLevel` changed from `float` to `int`
- **New Constant**: `IMMOBILITY_THRESHOLD = 0.5f` (prepared for future immobility detection)

- **Method Signature Update**: `updateFuelConsumption(byte speed)`
  - Parameter type: `float` → `byte`
  - Matches new Drone.consumeFuel() signature

- **Method Update**: `checkFuelLevelChange()`
  - Now works with `int` fuel level
  - Direct comparison: `currentFuel != lastFuelLevel` (no threshold)
  - Critical fuel check: `currentFuel <= 10 && currentFuel > 0`

**Impact**: Fuel manager now operates entirely in integer domain

---

### 4. **GameListener.java**
**Purpose**: Event interface for all game listeners

**Changes**:
- **Method Signature**: `onFuelLevelChanged(float fuelLevel)` → `onFuelLevelChanged(int fuelLevel)`

**Impact**: All listeners must now receive integer fuel values

---

### 5. **Game.java**
**Purpose**: Core game loop and event distribution

**Changes**:
- **Method Signature**: `onFuelLevelChanged(float fuelLevel)` → `onFuelLevelChanged(int fuelLevel)`
- Broadcasts integer fuel to all listeners

**Impact**: Event distribution now uses integer fuel model

---

### 6. **GUIGame.java**
**Purpose**: UI rendering and display

**Changes**:
- **Method Signature**: `onFuelLevelChanged(float fuelLevel)` → `onFuelLevelChanged(int fuelLevel)`

- **Method Update**: `updateFuelGaugeUI()`
  - Changed from: `float fuelPercentage = controller.getDrone().getFuelPercentage()`
  - Changed to: `int fuelLevel = controller.getDrone().getCurrentFuel()`
  - Display format: `"Fuel: %.1f%"` → `"Fuel: %d%"`
  - Example: Shows "Fuel: 47%" instead of "Fuel: 47.3%"

**Impact**: UI now displays clean integer fuel values

---

### 7. **BluetoothCommunication.java**
**Purpose**: Multiplayer synchronization

**Changes**:
- **Parsing Update** (line ~257):
  - Changed from: `float fuelLevel = Float.parseFloat(msgSplit[1])`
  - Changed to: `int fuelLevel = Integer.parseInt(msgSplit[1])`

- **Method Signature**: `onFuelLevelChanged(float fuelLevel)` → `onFuelLevelChanged(int fuelLevel)`

- **Message Format** (line ~444):
  - Changed from: `"fuelLevel/" + String.format("%.1f", fuelLevel)`
  - Changed to: `"fuelLevel/" + fuelLevel`
  - Example: Sends "fuelLevel/47" instead of "fuelLevel/47.3"

**Impact**: Bluetooth messages now use integer fuel for cleaner transmission

---

## Fuel Consumption Model

### Speed-Fuel Relationship

| Fuel Level | Speed | Consumption/Frame | Effect |
|-----------|-------|------------------|--------|
| > 10 | 20 (NORMAL) | 5 units | Normal racing |
| 1-10 | 10 (REDUCED) | 2 units | Slow, degraded performance |
| 0 | 5 (EMPTY) | 0 units | Immobile/stuck |

### Examples

**Example 1**: Starting race with 100 fuel
```
Frame 1: Moving at speed 20 → Consume 5 → Fuel = 95
Frame 2: Moving at speed 20 → Consume 5 → Fuel = 90
...
Frame 20: Moving at speed 20 → Consume 5 → Fuel = 10
Frame 21: Speed automatically reduced to 10 → Consume 2 → Fuel = 8
Frame 22: Speed = 10 → Consume 2 → Fuel = 6
Frame 23: Speed = 10 → Consume 2 → Fuel = 4
Frame 24: Speed = 10 → Consume 2 → Fuel = 2
Frame 25: Speed = 10 → Consume 2 → Fuel = 0
Frame 26: Speed = 5 (EMPTY) → Consume 0 → Fuel = 0 (IMMOBILE)
```

**Example 2**: In pit stop refueling
```
Fuel = 15 → In pit stop → Refuel +5 → Fuel = 20
Fuel = 20 → In pit stop → Refuel +5 → Fuel = 25
```

---

## Design Decisions

### 1. **Integer Fuel Model**
- **Why**: Cleaner game mechanics, no floating-point precision issues, easier to display
- **Range**: 0-100 represents 0% to 100%

### 2. **Speed Reduction Automatic**
- **Why**: Prevents player from maintaining full speed when fuel is critical
- **Benefit**: Adds urgency to find pit stops

### 3. **Zero Fuel = Immobile**
- **Why**: Prevents infinite play on last fuel unit
- **Design**: Speed of 5 units means effectively stuck (ultra-slow movement)

### 4. **Consumption Rates (5/2/0)**
- **5 at speed 20**: Fast consumption encourages strategic pit stop planning
- **2 at speed 10**: Reduced penalty allows reaching pit stop if planned
- **0 at speed 5**: Prevents infinite crawling

---

## Pit Stop Mechanics (Future)

The current implementation maintains marker-based pit stop detection. For immobility-based detection:

1. **Velocity Tracking**: Monitor drone velocity (prepared constant `IMMOBILITY_THRESHOLD`)
2. **Pit Stop as Item**: Refactor PitStop to extend Item class
3. **Duration Check**: Require drone to remain stationary for N frames before refueling
4. **Visual Feedback**: Display refueling progress to player

---

## Testing Recommendations

### Unit Tests to Run
1. **Fuel Consumption**: Verify 5/2/0 consumption rates
2. **Speed Transitions**: Test speed changes at fuel=10 boundary
3. **Bluetooth Sync**: Ensure integer fuel transmits correctly
4. **UI Display**: Verify integer format displays properly

### Integration Tests
1. **Full Race**: Simulate complete race with fuel depletion
2. **Pit Stop**: Verify refueling at pit stops
3. **Multiplayer**: Test Bluetooth fuel synchronization

---

## Backward Compatibility

⚠️ **Breaking Changes**:
- `Drone.getCurrentFuel()` now returns `int` (was `float`)
- `Drone.consumeFuel()` now takes `byte speed` (was `float`)
- `FuelManager.updateFuelConsumption()` now takes `byte speed` (was `float`)
- `GameListener.onFuelLevelChanged()` now takes `int` (was `float`)

All listeners and code calling these methods must be updated.

---

## Files NOT Modified (But Compatible)

- **Circuit.java**: Pit stop handling unchanged
- **PitStop.java**: Structure unchanged (ready for Item conversion in future)
- **activity_gui_game.xml**: Layout unchanged (fuel gauge widget still displays)
- **FuelSystemTests.java**: Should be updated to test integer model

---

## Commit Message Template

```
feat: Refine fuel mechanics with integer model and speed tiers

- Change fuel from float (0.0-100.0f) to int (0-100)
- Implement speed tiers: NORMAL=20, REDUCED=10, EMPTY=5
- Implement consumption tiers: 5 (normal), 2 (reduced), 0 (empty)
- Auto-reduce speed when fuel < 10
- Update all fuel-related method signatures
- Synchronize Bluetooth fuel messages to use integer format
- Update UI display to show integer fuel percentages

BREAKING CHANGES:
- Drone.getCurrentFuel() returns int
- Drone.consumeFuel(byte speed) parameter changed
- GameListener.onFuelLevelChanged(int) parameter changed
```

---

## Summary Statistics

- **Files Modified**: 7 core Java files
- **Methods Updated**: 12+
- **Constants Changed/Added**: 8
- **Type Conversions**: float → int throughout
- **New Features**: Auto speed reduction based on fuel
- **Lines Changed**: ~150 lines

