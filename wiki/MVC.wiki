J'ai modifié ce que vous aviez fait pour mettre en place le MVC correctement.

Nous avons besoin d'une classe qui gere tous le programme et le créé (pattern créateur et expert en information) j'ai donc fait une classe Reseau qui prend le graphe, les metros dans un tableau et les lignes qui contiennent les stations. C'est dans cette classe qu'on va lancer la methode run() qui est le moteur du programme. Elle remplace la classe Moteur que j'ai supprimé.

Le Controller ReseauController est ActionListener il prend les actions utilisateur, les traite et appel des methodes dans le modele. Il est créateur des vue via une classe abstraite que j'ai créé: ReseauView qui permet de créer une vue qui aura en attribut hérité le controller et le controller a en attribut le model (la classe Reseau).
Donc le controller fait le lien entre les vues et le model.

On a plus besoin d'un attribut graphe dans Interface, il suffit d'aller chercher celui de la classe Reseau du model en faisant: this.getController().getModel().getGraphe()

Bonne fin de week end