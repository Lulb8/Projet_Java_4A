# Mise à jour du jeu

Pour que les joueurs gardent un certain intérêt pour le jeu, il faut régulièrement apporter des changements, améliorations et bonus.
#### Comment faire pour éviter la coupure de service ?
Afin d'éviter la coupure de service, il faut faire en sorte de mettre à jour le jeu à un moment où il y a peu de joueurs en ligne de préférence (on peut déterminer cet horaire en regardant les statistiques d'utilisation des connexions) et mettre à jour le serveur en 2 temps afin de ne pas stopper les parties en cours. 

#### Que faire des parties en cours ?
En faisant la mise à jour en 2 temps, on peut laisser les joueurs actuellement en ligne finir leurs parties tandis qu'on installera la nouvelle version du jeu sur la deuxième partie du serveur. Une fois que toutes les parties présentes sur l'ancienne version du jeu sont finies, on la met à jour. Pendant ce temps les joueurs pourront commencer de nouvelles parties sur la nouvelle version du jeu.
#### Comment changer l'API pour que les joueurs utilisant une vieille version du client web puissent toujours jouer ? (rétro-compatibilité)
Pour assurer la rétro compatibilité de notre jeu, il faut créer plusieurs versions de notre api avec des noms différents à chaque fois.
#### Comment avertir les joueurs de la nouveauté une unique fois ?
Lors de la connexion du joueur pour la première fois depuis la dernière mise à jour, il aura un message de présentation des nouveautés qui sera enclenché en fonction de l'état d'un boolean dans la base de données. Ce boolean permet de dire si le joueur a vu le message ou non. À chaque nouvelle mise à jour ce paramètre sera remis à son état par défaut.
