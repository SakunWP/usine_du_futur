# Guide d'Intégration du Système de Carburant

## Étapes d'intégration dans le cycle de jeu

### 1. Initialisation du FuelManager

Dans `GUIGame.onCreate()` après la création du jeu et du contrôleur :

```java
// After game and controller creation
FuelManager fuelManager = new FuelManager(game, controller.getDrone());
```

Conservez une référence à `fuelManager` comme variable de classe.

### 2. Mise à jour périodique du carburant

Dans la boucle de jeu (généralement dans `DetectionTask` ou dans une thread de mise à jour) :

```java
// Get current drone speed (varies based on your implementation)
float currentSpeed = getCurrentDroneSpeed(); // You need to implement this

// Update fuel consumption every frame
fuelManager.updateFuelConsumption(currentSpeed);

// Update GUI
GUI_GAME_HANDLER.sendEmptyMessage(GUIGame.FUEL_GAUGE_UPDATE);
```

### 3. Détection des pit stops

Quand un marqueur AR est détecté :

```java
// In DetectionTask or your AR detection code
DetectionTask.Symbol detectedMarker = /* ... */;

// Check for pit stop
fuelManager.checkMarkerForPitStop(detectedMarker);
```

### 4. Configuration des pit stops sur le circuit

Lors de l'initialisation du circuit :

```java
// In circuit setup code
Circuit circuit = Circuit.getInstance();

// Define which markers are pit stops
PitStop pitStop1 = new PitStop(DetectionTask.Symbol.MARKER_1, 0);
circuit.addPitStop(DetectionTask.Symbol.MARKER_1, pitStop1);

PitStop pitStop2 = new PitStop(DetectionTask.Symbol.MARKER_5, 4);
circuit.addPitStop(DetectionTask.Symbol.MARKER_5, pitStop2);
```

## Adaptation de la vitesse en cas de carburant critique

Modifiez les méthodes de mouvement dans `DroneController` :

```java
public void moveForward() {
    if (deviceController != null && started) {
        Drone drone = DRONE;
        byte speed = NORMAL_SPEED;
        
        // Reduce speed if fuel is critical
        if (!drone.canMoveAtFullSpeed()) {
            speed = (byte)(NORMAL_SPEED / 2); // 50% speed reduction
        }
        
        deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(speed);
    }
}

public void moveBackward() {
    if (deviceController != null && running) {
        Drone drone = DRONE;
        byte speed = NEG_NORMAL_SPEED;
        
        if (!drone.canMoveAtFullSpeed()) {
            speed = (byte)(NEG_NORMAL_SPEED / 2);
        }
        
        deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(speed);
    }
}
```

## Implémentation du boost

Le boost consume plus rapidement le carburant :

```java
public void boost() {
    if (deviceController != null && running && DRONE.canMoveAtFullSpeed()) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DRONE.consumeFuel(50); // Consommation supplémentaire
                deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(BOOST_SPEED);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(NORMAL_SPEED);
            }
        }).start();
    }
}
```

## Gestion de la panne sèche

Si le carburant atteint zéro :

```java
public void moveForward() {
    if (deviceController != null && started) {
        if (DRONE.isOutOfFuel()) {
            // Complete stop
            deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(NO_SPEED);
            Toast.makeText(GUI_GAME, "Out of fuel! Find a pit stop!", Toast.LENGTH_SHORT).show();
        } else {
            // Normal movement
            deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(NORMAL_SPEED);
        }
    }
}
```

## Synchronisation multijoueur avancée

Pour une meilleure synchronisation, vous pouvez envoyer les mises à jour de carburant à intervalle régulier :

```java
// In a periodic update thread (e.g., every 500ms)
private Handler periodicUpdateHandler = new Handler();
private Runnable periodicUpdate = new Runnable() {
    @Override
    public void run() {
        if (game != null && game.isStarted() && !game.isFinished()) {
            float currentFuel = controller.getDrone().getCurrentFuel();
            game.onFuelLevelChanged(currentFuel);
        }
        periodicUpdateHandler.postDelayed(this, 500); // Every 500ms
    }
};

// Start in GUIGame.onCreate()
periodicUpdateHandler.post(periodicUpdate);

// Stop in GUIGame.onDestroy()
periodicUpdateHandler.removeCallbacks(periodicUpdate);
```

## Tests unitaires recommandés

```java
@Test
public void testFuelConsumption() {
    Drone drone = new Drone();
    float initialFuel = drone.getCurrentFuel();
    
    drone.consumeFuel(20); // Speed 20
    
    float expectedConsumption = 20 * 0.5f; // 10 units
    assertEquals(initialFuel - expectedConsumption, drone.getCurrentFuel(), 0.01f);
}

@Test
public void testFuelRefill() {
    Drone drone = new Drone();
    drone.setCurrentFuel(50);
    drone.setInPitStop(true);
    
    drone.refillFuel();
    
    assertEquals(52, drone.getCurrentFuel(), 0.01f); // 50 + 2
}

@Test
public void testCriticalFuel() {
    Drone drone = new Drone();
    drone.setCurrentFuel(5);
    
    assertFalse(drone.canMoveAtFullSpeed());
    assertTrue(drone.getCurrentFuel() > 0);
}
```

## Optimisation des performances

1. **Réduire la fréquence de mise à jour du GUI** :
   - Mettre à jour la jauge toutes les 10 frames au lieu de chaque frame
   
2. **Utiliser un pool de messages** :
   - Réutiliser les objets Message au lieu de les créer à chaque fois

3. **Limiter les notifications Bluetooth** :
   - Envoyer les mises à jour de carburant uniquement quand le changement est significatif (> 1%)

## Débogage

Activez les logs détaillés :

```java
// Dans FuelManager
Log.d(FUEL_MANAGER_TAG, "Fuel: " + drone.getCurrentFuel() + 
      "% | Speed: " + speed + 
      " | In PitStop: " + drone.isInPitStop());

// Dans Game
Log.d(GAME_TAG, "Critical fuel event triggered");

// Dans BluetoothCommunication
Log.d(BLUETOOTH_COMMUNICATION_TAG, "Fuel sync: " + fuelLevel + "%");
```

## Configurationdes marqueurs pour pit stops

Modifier le fichier de détection AR ou de configuration des marqueurs :

```
MARKER_0 = Départ/Arrivée
MARKER_1 = Pit Stop 1
MARKER_2 = Checkpoint
MARKER_3 = Checkpoint
MARKER_4 = Checkpoint
MARKER_5 = Pit Stop 2
```

## Fichiers modifiés

- ✅ `Drone.java` - Ajout du système de carburant
- ✅ `PitStop.java` - Création de la classe
- ✅ `Circuit.java` - Support des pit stops
- ✅ `FuelManager.java` - Gestion du cycle de carburant
- ✅ `Game.java` - Événements de carburant
- ✅ `GUIGame.java` - Interface utilisateur
- ✅ `GameListener.java` - Interface d'événements
- ✅ `BluetoothCommunication.java` - Synchronisation
- ✅ `activity_gui_game.xml` - Widget de jauge

## Prochaines étapes

1. Importer les modifications dans votre IDE
2. Configurer les pit stops dans votre circuit
3. Ajouter la logique d'appel au FuelManager dans votre boucle de jeu
4. Tester les scénarios de test
5. Ajuster les taux de consommation/ravitaillement selon votre équilibre de jeu
