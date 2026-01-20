# Système de Gestion du Carburant et des Stands de Ravitaillement

## Vue d'ensemble

Ce document décrit l'implémentation complète du système de gestion du carburant et des stands de ravitaillement (pit stops) pour l'application Super Jumping Sumo Kart en réalité augmentée.

## Architecture générale

### Classes principales

#### 1. **Drone.java** (Modifié)
Classe représentant un drone joueur avec les extensions de carburant :

**Nouveaux attributs :**
- `MAX_FUEL` : Capacité maximale de carburant (100.0f)
- `currentFuel` : Niveau actuel de carburant
- `FUEL_CONSUMPTION_RATE` : Taux de consommation par unité de vitesse (0.5f)
- `FUEL_REFILL_RATE` : Taux de recharge en pit stop (2.0f)
- `inPitStop` : Booléen indiquant si le drone est en pit stop
- `CRITICAL_FUEL_THRESHOLD` : Seuil de carburant critique (10.0f)

**Nouvelles méthodes :**
- `getCurrentFuel()` : Récupère le niveau actuel de carburant
- `getFuelPercentage()` : Récupère le carburant en pourcentage (0-100)
- `setCurrentFuel(float)` : Définit le niveau de carburant
- `consumeFuel(float speed)` : Consomme du carburant selon la vitesse
- `refillFuel()` : Recharge le carburant en pit stop
- `canMoveAtFullSpeed()` : Vérifie si le drone peut se déplacer à vitesse normale
- `isOutOfFuel()` : Vérifie si le carburant est à zéro
- `isInPitStop()` : Vérifie si le drone est en pit stop
- `setInPitStop(boolean)` : Définit le statut de pit stop
- `resetFuel()` : Réinitialise le carburant pour une nouvelle course

#### 2. **PitStop.java** (Nouveau)
Classe représentant un stand de ravitaillement sur le circuit :

**Attributs :**
- `markerSymbol` : Symbole du marqueur AR associé au pit stop
- `positionIndex` : Position du pit stop sur le circuit
- `isActive` : Booléen indiquant si le pit stop est actuellement actif

**Méthodes :**
- Getters et setters pour accéder aux propriétés

#### 3. **Circuit.java** (Modifié)
Classe de gestion du circuit avec support des pit stops :

**Nouveaux attributs :**
- `pitStops` : HashMap des pit stops présents sur le circuit

**Nouvelles méthodes :**
- `addPitStop(DetectionTask.Symbol, PitStop)` : Ajoute un pit stop
- `getPitStop(DetectionTask.Symbol)` : Récupère un pit stop par son marqueur
- `isPitStop(DetectionTask.Symbol)` : Vérifie si un marqueur est un pit stop
- `getPitStops()` : Récupère tous les pit stops

#### 4. **FuelManager.java** (Nouveau)
Classe gestionnaire du système de carburant :

**Responsabilités :**
- Gestion de la consommation de carburant pendant la course
- Détection automatique des pit stops
- Gestion du ravitaillement
- Notification des changements de niveau de carburant

**Méthodes principales :**
- `updateFuelConsumption(float speed)` : Mise à jour périodique
- `checkMarkerForPitStop(DetectionTask.Symbol)` : Détection des pit stops
- `checkFuelLevelChange()` : Notification des changements
- `reset()` : Réinitialisation pour une nouvelle course

#### 5. **Game.java** (Modifié)
Classe de gestion du jeu avec support des événements de carburant :

**Nouvelles méthodes :**
- `onPlayerEntersPitStop()` : Notification d'entrée en pit stop
- `onPlayerExitsPitStop()` : Notification de sortie de pit stop
- `onFuelLevelChanged(float)` : Notification de changement de carburant
- `onCriticalFuel()` : Notification de carburant critique

#### 6. **GUIGame.java** (Modifié)
Interface utilisateur du jeu avec affichage du carburant :

**Nouveaux éléments UI :**
- `fuelGaugeTextView` : Affichage de la jauge de carburant
- `FUEL_GAUGE_UPDATE` : Constante de message pour mise à jour GUI

**Nouvelles méthodes :**
- `updateFuelGaugeUI()` : Mise à jour de l'affichage du carburant
- Implémentations des méthodes GameListener pour pit stops et carburant

#### 7. **GameListener.java** (Modifié)
Interface d'événements du jeu avec ajout d'événements de carburant :

**Nouvelles méthodes :**
- `onPlayerEntersPitStop()` : Événement d'entrée en pit stop
- `onPlayerExitsPitStop()` : Événement de sortie de pit stop
- `onFuelLevelChanged(float)` : Événement de changement de carburant
- `onCriticalFuel()` : Événement de carburant critique

#### 8. **BluetoothCommunication.java** (Modifié)
Classe de communication Bluetooth avec synchronisation du carburant :

**Nouveaux messages :**
- `entersPitStop` : Notification d'entrée en pit stop
- `exitsPitStop` : Notification de sortie de pit stop
- `fuelLevel/{level}` : Synchronisation du niveau de carburant
- `criticalFuel` : Notification de carburant critique

## Flux de jeu

### Initialisation
1. À la création du drone, le carburant est initialisé à 100%
2. FuelManager est créé avec référence au drone et au jeu

### Pendant la course
1. **Mise à jour périodique :**
   - Chaque frame, `updateFuelConsumption(speed)` est appelé
   - Le carburant diminue selon la vitesse actuelle
   - Si en pit stop : le carburant augmente automatiquement

2. **Détection des pit stops :**
   - Quand un marqueur est détecté : `checkMarkerForPitStop(marker)` est appelé
   - Si le marqueur correspond à un pit stop : le drone entre en mode ravitaillement
   - Sortie automatique quand on quitte la zone du marqueur

3. **Notifications :**
   - Changement de carburant : notification à tous les GameListeners
   - Carburant critique : notification spéciale et indicateurs visuels
   - En pit stop : notifications visuelles et sonores optionnelles

### Synchronisation multijoueur
- Les changements de carburant sont envoyés via Bluetooth
- Les pit stops sont synchronisés entre les deux joueurs
- Le carburant critique déclenche une notification à l'adversaire

## Configuration des pit stops

Pour ajouter des pit stops à un circuit, utiliser dans le code d'initialisation du circuit :

```java
Circuit circuit = Circuit.getInstance();
PitStop pitStop1 = new PitStop(DetectionTask.Symbol.MARKER_1, 0);
circuit.addPitStop(DetectionTask.Symbol.MARKER_1, pitStop1);
```

## Intégration avec l'interface utilisateur

La jauge de carburant est affichée en haut de l'écran avec :
- **Couleur verte** : > 50% de carburant
- **Couleur orange** : 20-50% de carburant
- **Couleur rouge** : < 20% de carburant (critique)

Les toast notifications informent le joueur des événements importants :
- "Refueling... Stay in position!" - Entrée en pit stop
- "Pit stop exited" - Sortie du pit stop
- "CRITICAL FUEL! Find a pit stop!" - Carburant critique

## Taux de consommation et ravitaillement

### Consommation
- **Taux de base** : 0.5 unité de carburant par unité de vitesse
- **Formule** : `consommation = vitesse × 0.5`
- **Vitesse maximale** : ~100 (boost)
- **Exemple** : À vitesse 20 → consommation de 10 carburant/update

### Ravitaillement
- **Taux de ravitaillement** : 2.0 unité de carburant par update
- **Temps estimé pour plein complet** : ~50 updates (~2 secondes à 25 FPS)

## Limites de mouvement

### Mode normal (carburant > 10%)
- Vitesse normale : 20
- Vitesse rapide : 40
- Boost : 100

### Mode critique (carburant ≤ 10%)
- Vitesse réduite à 50% de la normale
- Pas de boost possible
- Accélération réduite

## Gestion des pénalités

Quand le carburant est critique (< 10%) :
1. Les performances du drone sont dégradées
2. Une notification visuelle (texte rouge) l'indique
3. Un toast notification alerte le joueur
4. Le joueur est poussé à trouver un pit stop

## Tests et validation

### Scénarios de test
1. **Test de consommation** : Vérifier que le carburant diminue à la vitesse appropriée
2. **Test de pit stop** : Vérifier que le ravitaillement fonctionne correctement
3. **Test de performance dégradée** : Vérifier que la vitesse est réduite avec carburant faible
4. **Test multijoueur** : Vérifier la synchronisation Bluetooth
5. **Test d'interface** : Vérifier l'affichage de la jauge et les notifications

## Notes de développement

- La consommation de carburant est intégrée dans la boucle de jeu
- Les pit stops sont détectés automatiquement via les marqueurs AR
- La synchronisation Bluetooth gère l'état du carburant en temps réel
- L'interface GUI est mise à jour via le Handler pour éviter les blocages

## Améliorations futures

1. Effets sonores pour les pit stops
2. Animation visuelle de la jauge de carburant
3. Système de bonus de carburant au franchissement de checkpoints
4. Dégats au contact diminuant la capacité max de carburant
5. Power-ups pour réduire la consommation
