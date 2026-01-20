# Document de Recette - Système de Carburant et Stands de Ravitaillement

## Objectif de la recette
Valider que le système de gestion du carburant et des stands de ravitaillement fonctionne correctement et répond à toutes les exigences spécifiées.

## Date de la recette
À définir après l'intégration

## Examinateurs
- Représentant du client
- Lead développeur
- Testeur qualité

---

## EXIGENCE 1 : Jauge de carburant

### Critères d'acceptation
- ✓ Chaque drone dispose d'une jauge de carburant initialisée à 100% au démarrage
- ✓ Le niveau de carburant est affiché en continu sur l'écran
- ✓ L'affichage montre le pourcentage actuel avec une décimale (ex: "Fuel: 87.5%")
- ✓ L'affichage change de couleur selon le niveau :
  - Vert : > 50%
  - Orange : 20-50%
  - Rouge : < 20%

### Procédure de test
1. Lancer une course
2. Vérifier que la jauge affiche "Fuel: 100.0%"
3. Vérifier que le texte est vert
4. Observer l'affichage pendant 30 secondes
5. Vérifier que la jauge diminue progressivement

### Résultats attendus
```
Début : Fuel: 100.0% [Texte vert]
À 60km/h : Fuel: 85.3% [Texte vert]
À 80km/h : Fuel: 42.1% [Texte orange]
À 100km/h : Fuel: 15.7% [Texte rouge]
```

### Statut de validation
- ☐ Conforme
- ☐ Non conforme
- ☐ Conforme avec remarques

**Remarques** : _________________

---

## EXIGENCE 2 : Consommation de carburant

### Critères d'acceptation
- ✓ Le carburant diminue automatiquement lorsque le drone est en mouvement
- ✓ La consommation dépend de la vitesse (plus rapide = plus de consommation)
- ✓ À l'arrêt (vitesse 0), aucune consommation

### Procédure de test

#### Test 2a : Consommation au repos
1. Lancer une course et arrêter le drone
2. Observer la jauge pendant 10 secondes
3. Vérifier qu'elle ne change pas

**Résultat** : Fuel restant après 10s : _________ (doit être identique)

#### Test 2b : Consommation en mouvement lent (vitesse 20)
1. Avancer lentement pendant 5 secondes
2. Noter le carburant initial et final
3. Calculer la consommation

**Résultats** :
- Carburant initial : _________
- Carburant après 5s : _________
- Consommation : _________ (attendu: ~5 unités)

#### Test 2c : Consommation en mouvement rapide (vitesse 40)
1. Avancer rapidement pendant 5 secondes
2. Noter le carburant initial et final
3. Calculer la consommation

**Résultats** :
- Carburant initial : _________
- Carburant après 5s : _________
- Consommation : _________ (attendu: ~10 unités)

### Vérification de la proportionnalité
La consommation à vitesse 40 devrait être 2x celle à vitesse 20 ✓

### Statut de validation
- ☐ Conforme
- ☐ Non conforme
- ☐ Conforme avec remarques

**Remarques** : _________________

---

## EXIGENCE 3 : Avertissements de carburant

### Critères d'acceptation
- ✓ Avertissements visuels quand carburant atteint des niveaux critiques
- ✓ Toast notifications pour informer le joueur
- ✓ Changement de couleur du texte (rouge quand < 20%)

### Procédure de test

#### Test 3a : Changement de couleur
1. Lancer une course et observer la jauge
2. À 50% : doit être orange
3. À 20% : doit être rouge

**Résultats** :
- À 100% : _________ (attendu: vert) ✓
- À 50% : _________ (attendu: orange) ✓
- À 20% : _________ (attendu: rouge) ✓
- À 10% : _________ (attendu: rouge intense) ✓

#### Test 3b : Notifications texte
1. Attendre que le carburant atteigne < 10%
2. Vérifier qu'une notification "CRITICAL FUEL!" s'affiche

**Résultats** :
- Toast notification apparue ? ☐ Oui ☐ Non
- Message correct ? ☐ Oui ☐ Non
- Timing approprié ? ☐ Oui ☐ Non

### Statut de validation
- ☐ Conforme
- ☐ Non conforme
- ☐ Conforme avec remarques

**Remarques** : _________________

---

## EXIGENCE 4 : Panne sèche

### Critères d'acceptation
- ✓ Quand carburant = 0, drone ne peut plus se déplacer
- ✓ Mode de mouvement réduit activé quand carburant < 10%
- ✓ Les performances sont significativement dégradées (50% de réduction)

### Procédure de test

#### Test 4a : Mode critique (10-0%)
1. Laisser le carburant atteindre 10%
2. Tenter d'avancer
3. Observer que la vitesse est réduite

**Résultats** :
- Vitesse normale à carburant haut : _________
- Vitesse à 10% carburant : _________ (attendu: 50% moins)
- Ratio vitesse réduite/normale : _________ (attendu: 0.5)

#### Test 4b : Panne sèche complète (0%)
1. Vider complètement le carburant
2. Tenter de se déplacer (avant, arrière)
3. Vérifier que le drone ne bouge pas

**Résultats** :
- Drone peut-il avancer à 0% ? ☐ Oui ☐ Non (attendu: Non)
- Drone peut-il reculer à 0% ? ☐ Oui ☐ Non (attendu: Non)
- Notification d'arrêt ? ☐ Oui ☐ Non

### Statut de validation
- ☐ Conforme
- ☐ Non conforme
- ☐ Conforme avec remarques

**Remarques** : _________________

---

## EXIGENCE 5 : Stands de ravitaillement - Positionnement

### Critères d'acceptation
- ✓ Les stands sont positionnés sur le circuit via marqueurs AR
- ✓ Au moins 2 pit stops doivent être présents sur le circuit
- ✓ Les pit stops sont bien matérialisés en réalité augmentée

### Procédure de test
1. Consulter la configuration du circuit
2. Vérifier la liste des marqueurs
3. Compter le nombre de pit stops

**Résultats** :
- Nombre de marqueurs totaux : _________
- Nombre de pit stops : _________ (attendu: minimum 2)
- Pit stops bien visibles en AR ? ☐ Oui ☐ Non
- Marqueurs spécifiques : _________________

### Statut de validation
- ☐ Conforme
- ☐ Non conforme
- ☐ Conforme avec remarques

**Remarques** : _________________

---

## EXIGENCE 6 : Ravitaillement en pit stop

### Critères d'acceptation
- ✓ Entrée automatique en pit stop détectée
- ✓ Carburant se recharge progressivement
- ✓ Drone immobilisé ou ralenti durant le ravitaillement
- ✓ Joueur peut quitter avant fin du ravitaillement complet

### Procédure de test

#### Test 6a : Détection d'entrée en pit stop
1. Diriger le drone vers un pit stop
2. Vérifier que "Refueling..." s'affiche
3. Vérifier que le drone ralentit/s'immobilise

**Résultats** :
- Notification affichée ? ☐ Oui ☐ Non
- Délai de détection : _________ ms
- Drone ralentit ? ☐ Oui ☐ Non

#### Test 6b : Recharge progressive
1. Entrer en pit stop avec 20% de carburant
2. Observer l'augmentation du carburant
3. Noter le temps pour passer de 20% à 100%

**Résultats** :
- Carburant initial : 20%
- Carburant après 5s : _________%
- Carburant après 10s : _________%
- Temps pour plein complet : _________ s (attendu: ~30-50s)
- Augmentation linéaire ? ☐ Oui ☐ Non

#### Test 6c : Sortie anticipée
1. Entrer en pit stop
2. Attendre 3 secondes
3. Quitter le pit stop
4. Vérifier que le ravitaillement s'arrête

**Résultats** :
- Carburant au départ : 20%
- Carburant après 3s de ravit. : _________%
- Carburant après sortie : _________%
- Augmentation continue après sortie ? ☐ Oui ☐ Non (attendu: Non)

### Statut de validation
- ☐ Conforme
- ☐ Non conforme
- ☐ Conforme avec remarques

**Remarques** : _________________

---

## EXIGENCE 7 : Multijoueur

### Critères d'acceptation
- ✓ Niveaux de carburant synchronisés entre joueurs
- ✓ Passages aux stands synchronisés
- ✓ Règles identiques pour tous les joueurs

### Procédure de test

#### Test 7a : Synchronisation carburant
1. Lancer une course multijoueur
2. Observer les jauges sur les deux appareils
3. Noter les valeurs identiques

**Résultats** :
- Joueur 1 initial : 100%
- Joueur 2 initial : 100% ✓
- Joueur 1 après 10s accélération : _________
- Joueur 2 simultané : _________ (doit être identique)
- Écart : _________ % (attendu: < 2%)

#### Test 7b : Synchronisation pit stops
1. Un joueur entre en pit stop
2. Vérifier que l'autre voit la notification
3. Comparer les taux de ravitaillement

**Résultats** :
- Joueur 1 en pit stop : Carburant 30% → 40% en 5s
- Joueur 2 reçoit notification ? ☐ Oui ☐ Non
- Taux identique ? ☐ Oui ☐ Non

#### Test 7c : Performance dégradée synchronisée
1. Laisser le carburant d'un joueur atteindre < 10%
2. Vérifier que l'autre voit la dégradation
3. Comparer les vitesses

**Résultats** :
- Notification critique reçue ? ☐ Oui ☐ Non
- Vitesses réduites identiques ? ☐ Oui ☐ Non

### Statut de validation
- ☐ Conforme
- ☐ Non conforme
- ☐ Conforme avec remarques

**Remarques** : _________________

---

## TESTS DE PERFORMANCE ET DE STABILITÉ

### Performance
- Frame rate stable ? ☐ Oui ☐ Non
- Pas de ralentissements dus au carburant ? ☐ Oui ☐ Non
- Mémoire stable ? ☐ Oui ☐ Non (pas de fuite)

### Stabilité
- Pas de crash lors de panne sèche ? ☐ Oui ☐ Non
- Pas de crash lors du ravitaillement ? ☐ Oui ☐ Non
- Pas de crash multijoueur ? ☐ Oui ☐ Non

### Compatibilité
- Fonctionne sur Android 7+ ? ☐ Oui ☐ Non
- Compatible avec tous les appareils testés ? ☐ Oui ☐ Non

---

## RÉSUMÉ DE VALIDATION

### Exigences validées
| Exigence | Statut | Remarques |
|----------|--------|-----------|
| 1. Jauge de carburant | ☐ OK | _________ |
| 2. Consommation | ☐ OK | _________ |
| 3. Avertissements | ☐ OK | _________ |
| 4. Panne sèche | ☐ OK | _________ |
| 5. Stands - Positionnement | ☐ OK | _________ |
| 6. Ravitaillement | ☐ OK | _________ |
| 7. Multijoueur | ☐ OK | _________ |

### Performance et stabilité
- Performance : ☐ OK ☐ Dégradée ☐ Non testée
- Stabilité : ☐ OK ☐ Crashes ☐ Non testée
- Compatibilité : ☐ OK ☐ Problèmes ☐ Non testée

### Verdict final
**☐ CONFORME** - Système validé, peut être livré
**☐ CONFORME AVEC REMARQUES** - Fonctionnel mais points à améliorer
**☐ NON CONFORME** - Doit être révisé avant livraison

### Anomalies identifiées
1. _________________________________
2. _________________________________
3. _________________________________

### Améliorations suggérées
1. _________________________________
2. _________________________________
3. _________________________________

---

## Signature et approbation

**Testeur** : _________________ **Date** : _________

**Lead Développeur** : _________________ **Date** : _________

**Client** : _________________ **Date** : _________

---

**Document version** : 1.0
**Date de création** : 20 janvier 2026
**Dernière modification** : 20 janvier 2026
