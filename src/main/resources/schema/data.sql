
-- Ajout des Caractéristiques
INSERT INTO caracteristique (idCaracteristique, nom, code, description) VALUES
    (1, 'Intelligence', 'INT', 'Permet de résoudre des énigmes, de réaliser des expériences scientifiques, de construire des raisonnements logiques…'),
    (2, 'Réflexes', 'RÉF', 'Sert à combattre, esquiver et effectuer des actions nécessitant des réactions rapides et des gestes précis.'),
    (3, 'Dextérité', 'DEX', 'Pour les attaques à distance et tout ce qui implique de faire appel à l''équilibre et à la coordination œil-main.'),
    (4, 'Corps', 'COR', 'S''utilise lorsqu''il faut faire appel à la force brute, comme pour les bagarres ou les prouesses physiques. Le corps représente aussi l''endurance nécessaire pour résister aux maladies ou à la fatigue.'),
    (5, 'Vitesse', 'VIT', 'Représente la vitesse de déplacement de votre personnage. Vous pouvez utiliser cette caractéristique pour semer les poursuivants ou calculer la distance parcourue dans un temps donné.'),
    (6, 'Empathie', 'EMP', 'Pour gérer les affaires de cœur et les émotions. La séduction et la persuasion font appel à cette caractéristique.'),
    (7, 'Technique', 'TECH', 'Pour utiliser des mécanismes ou créer des objets avec précision. La technique sert également à manier des machines de siège et à installer des pièges.'),
    (8, 'Volonté', 'VOL', 'Sert à intimider, mais aussi à réaliser des tests magiques et de résistance mentale. La volonté dénote votre capacité à aller de l''avant et votre degré de maîtrise de la magie.'),
    (9, 'Chance', 'CHA', 'La chance est une réserve de points que vous pouvez utiliser pour retourner les situations à votre avantage. Avant d''effectuer un jet de compétence (ou un jet de sauvegarde contre la mort), pendant votre tour ou en défense pendant le tour d''un adversaire, vous pouvez ajouter des points de chance. Vous bénéficiez de +1 par point ajouté, sachant que vous devez décider du montant dépensé avant de lancer les dés. Votre réserve de chance se reconstitue au début de chaque session de jeu.'),
    (10, 'Vigueur', 'VIG', 'Votre valeur de Vigueur correspond au nombre de points de Chaos que vous pouvez canaliser sans vous blesser. Vous subissez des dégâts lorsque le coût d''endurance de tous les sorts que vous lancez pendant un round est supérieur à cette valeur.'),
    (11, 'Étourdissement', 'ÉTOU', 'Pour savoir à quel point votre personnage peut résister aux coups avant de finir étourdi ou inconscient, il faut se reporter à sa valeur d''étourdissement. Lorsque vous devez effectuer un jet de sauvegarde d''étourdissement, vous devez obtenir un résultat inférieur à cette caractéristique ou être étourdi.'),
    (12, 'Course', 'VIT', 'Cette valeur correspond à votre vitesse quand vous courez à une allure raisonnable. En un tour, vous pouvez parcourir un nombre de mètres égal à votre valeur de course.'),
    (13, 'Saut', 'SAU', 'La valeur de Saut correspond au nombre de mètres que vous parcourez en sautant après avoir pris de l''élan.'),
    (14, 'Points de santé', 'PS', 'La santé correspond au nombre de points de dégâts que vous pouvez subir avant de passer en état de mort imminente et de succomber à une hémorragie.'),
    (15, 'Endurance', 'END', 'L''endurance représente l''énergie que vous pouvez dépenser lors d''un effort physique ou d''un acte magique avant de finir épuisé (elle représente également la difficulté à vous mettre K.O.). Lorsque vous n''avez plus de points d''endurance, vous êtes étourdi et vous ne pouvez rien faire à part vous remettre. Lorsque vous n’en avez plus, vous devenez épuisé et ne pouvez plus rien faire à part récupérer. Lorsque vous lancez un sort, que vous effectuez des actions supplémentaires en combat, que vous utilisez certaines capacités ou que vous subissez des dégâts non létaux, vous piochez aussi dans votre réserve d''endurance. En général, 1 minute d’effort intense ou 1 heure de travail répétitif fait perdre 2 points d''endurance. Quand vous effectuez une action Récupérer, vous regagnez un nombre de points d''endurance égal à votre valeur de récupération.'),
    (16, 'Encombrement', 'ENC', 'Cette valeur reflète le poids que vous pouvez porter sans être ralentis. Dès que vous êtes encombré, vous diminuez de -1 vos valeurs de RÉF, DEX et VIT pour chaque tranche de 5 points au-dessus de votre valeur d''encombrement, avec un minimum de 1. Vous pouvez soulever au maximum 50 fois votre valeur de corps en kilogrammes.'),
    (17, 'Récupération', 'RÉC', 'La récupération indique le nombre de points de santé que vous regagnez par jour de repos au calme dans un lit. Pour retrouver ces points de santé, quelqu’un doit d''abord réussir un jet de premiers soins ou de mains thérapeutiques sur vous.'),
    (18, 'Piedsf', 'Pieds', 'Cette caractéristique indique le nombre de dégâts non létaux que vous infligez avec un coup de poing.'),
    (19, 'Poings', 'Poings', 'Cette caractéristique indique le nombre de dégâts non létaux que vous infligez avec un coup de pied.');

ALTER TABLE caracteristique ALTER COLUMN idCaracteristique RESTART WITH 20;

-- Ajout de Compétences
INSERT INTO competence (idCompetence, nom, codeCaracteristique, description, descriptionBase10, descriptionBase13, descriptionBase16, descriptionBase20) VALUES
    (1, 'Connaissance de la rue', 'INT', 'Cette compétence ne concerne pas la géographie urbaine à proprement parler, mais plutôt le fonctionnement de cet environnement.', 'Vous en savez suffisamment pour éviter les voyous et vous rendre dans les quartiers les plus sûrs de la ville.', 'Vous pouvez en général déterminer quelle faction exerce son influence sur un quartier donné et expliquer la raison de cette domination.', 'Vous pouvez récolter une quantité d’informations impressionnantes sur une zone rien qu’en l’observant, mais aussi identifier les personnalités importantes de la ville et leurs relations.', 'Vous évaluez la situation en un clin d’œil. Vous en savez assez pour compter parmi les habitants.' ),
    (2, 'Connaissance des monstres', 'INT', 'La somme d’information que vous possédez à propos des monstres.', 'Vous êtes capable de différencier un nekker d’une goule.','Vous savez classer les monstres au sein des diverses catégories générales.', 'Vous êtes au fait des différentes forces et faiblesses que possèdent les créatures les plus communes.', 'Il est rare que vous rencontriez des monstres que vous seriez incapable d’analyser en quelques secondes, un exploit dont même un sorceleur serait fier.'),
    (3, 'Déduction', 'INT', 'La capacité à formuler des conclusions grâce aux indices dont vous disposez.', 'Vos intuitions vous mettent en général sur la bonne piste.', 'Vos ressentiments sont presque toujours fondés, vous pouvez même les étayer à l’aide de raisonnements logiques.', 'Il vous suffit de quelques indices pour avoir ce qui s’est passé ou ce qui pourrait advenir.', 'Vous ne vous trompez presque jamais dans vos déductions. Vous êtes capables de trouver des réponses même lorsque vous disposez de preuves partielles.' ),
    (4, 'Éducation', 'INT', 'Reflète votre niveau d’instruction.', 'Vos parents vous ont légué des connaissances sommaires sur la marche du monde.', 'Vous avez probablement bénéficié des leçons d’un mentor ou eu la chance de fréquenter l’une des rares écoles ouvertes dans votre région natale.', 'Vous avez acquis un savoir encyclopédique en allant par exemple étudier à Oxenfurt ou dans une autre académie du genre.', 'Vous êtes un érudit capable de rivaliser d’intelligence avec des professeurs d’université et des mages siégeant aux conseils royaux.' ),
    (5, 'Enseignement', 'INT', 'La capacité à dispenser son savoir. Vous n’êtes pas tenu de maîtriser cette capacité pour en enseigner d’autres, mais elle vous facilitera la tâche.', 'Vous savez expliquer du début à la fin un procédé simple à votre élève, mais sans que ce dernier saisisse à coup sûr tous les tenants et aboutissants de votre cheminement.', 'Vous pouvez enseigner les bases d’une compétence à un élève attentif sans rencontrer de problème particulier.', 'Si vous disposez de suffisamment de temps, vous pouvez apprendre des procédés plus complexes à vos étudiants, même s’ils ne sont guère attentifs.', 'Vous êtes un maître pédagogue capable d’intéresser n’importe qui. Puisque vos leçons font toujours mouche du premier coup, vous avez rarement besoin de vous répéter.' );

ALTER TABLE competence ALTER COLUMN idCompetence RESTART WITH 6;

-- Ajout de Professions
INSERT INTO profession (idProfession, nom, codeCaracteristique, competenceExclusive, description) VALUES
    (1, 'Artisan', 'TECH', 'Rafistolage', 'Un artisan talentueux peut effectuer des réparations de fortune sur une armure ou une arme pour qu’elle reste utilisable le temps du combat. Il pourra par exemple nouer deux parties d’une corde d’arc rompue, affûter la lame d’une épée ou clouer une plaque de métal sur un bouclier fendu. L’artisan consacre un tour à cette action. Il effectue alors un jet de rafistolage dont le SD est égal au SD de fabrication de l’objet -3. S’il réussit, l’armure brisée regagne la moitié de son PA total, ou la moitié de sa fiabilité dans le cas d’une épée ou d’un bouclier. Tant qu’elle n’est pas correctement réparée à l’aide d’une forge, une arme n’inflige que la moitié des dégâts normaux.'),
    (2, 'Barde', 'EMP', 'Prestation', 'Le barde est un compagnon des plus précieux, surtout quand le groupe commence à manquer d''argent. Il peut réaliser un jet de prestation et se donner en spectacle sur la place de la ville durant une heure. Le résultat du jet correspond au montant qu’il a récolté en se produisant dans la rue. Un échec critique peut diminuer le résultat du jet, et si le total devient négatif, cela signifie que la barde n’a pas réussi à recueillir une seule pièce. Il se fait huer par les habitants pour sa piètre performance et subit un malus de -2 en charisme lorsqu’il interagit avec les citadins jusqu''à la fin de la journée.'),
    (3, 'Criminel', 'INT', 'Paranoïa exercée', 'Qu''ils soient assassins, voleurs faussaires ou contrebandiers, tous les hors-la-loi ont en commun une paranoïa constante qui leur permet d’éviter les problèmes. Lorsqu’un criminel arrive à moins de 10m d''un piège (ce qui inclut les pièges expérimentaux, les chausses-trappes des hommes d''armes et les embuscades), il effectue immédiatement un jet de paranoïa exercée dont le SD est égal à celui requis pour déceler le piège, au résultat du jet de furtivité du groupe posté en embuscade ou au SD fixé par le MJ. Même s''il n''arrive pas à repérer le piège, il sentira au fond de lui-même que quelque-chose ne va pas.'),
    (4, 'Docteur', 'TECH', 'Mains thérapeutiques', 'N’’importe qui est capable d’’appliquer un onguent ou de bander une blessure, mais seul un docteur a reçu la formation nécessaire pour accomplir des actes chirurgicaux d’’une grande complexité. Un docteur avec mains thérapeutiques est le seul personnage capable de soigner une blessure critique. Pour soigner une blessure critique, il doit réussir un nombre de jets de mains thérapeutiques qui dépend de la gravité de cette blessure. Le SD du jet est aussi basé sur ce paramètre. Les mains thérapeutiques peuvent aussi servir lors des actions de premiers soins.'),
    (5, 'Homme d''arme', 'COR', 'Dur à cuire', 'Les véritables hommes d’’armes issus, par exemple, des Stries Bleues témériennes ou de la brigade Imprera de Nilfgaard sont des vétérans endurcis qui n’’abandonnent jamais et ne se rendent pas. Lorsque les points de santé d’’un homme d’’armes tombent à 0 ou moins, il peut effectuer un jet de dur à cuire dont le SD est égal au double de son score de santé négatif. S’’il échoue, il entre en état de mort imminente selon les règles habituelles. S’’il le réussit, il peut continuer à se battre comme s’’il avait atteint son seuil de blessures. Lorsqu’’il subit des dégâts, il réalise un nouveau jet dont le SD est calculé selon son nouveau score de santé.'),
    (6,'Mage','INT', 'Exercice de la magie', 'Pour devenir pleinement un mage, une personne sensible à la magie doit apprendre les bases de cet art au sein d’’une académie de magie. Un mage peut réaliser un jet d’exercice de la magie dès qu’il se trouve face à un phénomène magique ou un sort inconnu, ou encore lorsqu’il analyse une théorie de la magie. Le SD du jet est fixé par le MJ. En cas de réussite, le mage apprend tout ce qu’’il y a à savoir sur le phénomène en question. Un jet d’exercice de la magie peut aussi servir à détecter la magie en cours d’utilisation ou les spectres.'),
    (7, 'Marchand', 'INT', 'Grand voyageur', 'Un marchand ordinaire gagne sa vie grâce à son échope qui attire les clients alentours. En revanche, un commerçant itinérant part à la rencontre de ses clients. Il parcourt les routes du monde entier, ce qui lui permet d’’en apprendre beaucoup sur les peuples qu’’il croise. Un marchand peut effectuer un jet de grand voyageur chaque fois qu’’il désire connaître une anecdote concernant un objet, une culture ou une région particulière. Le MJ fixe le SD du jet. Si ce dernier réussit, le marchand se rappelle la réponse à la question posée grâce aux souvenirs datant de son dernier voyage dans le lieu concerné.'),
    (8, 'Prêtre', 'EMP', 'Initié des dieux', 'Les temples du monde entier sont bien souvent des lieux chaleureux qui aident les fidèles et accueillent les nouveaux convertis. Un prêtre peut réaliser un jet d’initié des dieux dont le SD est fixé par les MJ dans les églises de cette religion pour obtenir un hébergement gratuit, des soins et d’autres services, à la discrétion du MJ. L’initiation des dieux fonctionne aussi auprès des croyants de la même confession, bien qu’ils aient moins à offrir qu’un temple. N’oubliez pas qu’initié des dieux ne fonctionne jamais avec les membres d’une autre religion.'),
    (9, 'Sorceleur', 'INT', 'Formation de soceleur', 'Les sorceleurs passent les premières années de leur vie confinés entre les murs de leur forteresse, où ils étudient d’énormes tomes poussiéreux et suivent un entraînement martial démentiel. De nombreux savants ont démontré que les meilleures armes d’un sorceleur étaient sa connaissance des monstres ainsi que ses capacités d’’adaptation. Il peut donc diminuer les pénalités provoquées par un environnement hostile ou un relief accidenté d’’une valeur égale à la moitié de son score de formation de sorceleur (minimum 1). Vous pouvez également utiliser formation de sorceleur dans les situations requérant l’’usage de la compétence connaissance des monstres.'),
    (10, 'Noble', '', 'Prestige', 'Qu’’il soit noble de naissance ou qu’’il ait acquis ses titres en accomplissant des faits d’’armes, l’’aristocrate affiche son statut privilégié afin que nul ne l’ignore. Les paysans peuvent bien maudire les familles nobles et les ridiculiser à l’abri dans leurs chaumières, mais aucun d’’entre eux n’’oserait les insulter en face. Un noble ajoute son niveau de prestige à son score de réputation lorsqu’’il se trouve sur sa terre natale ou dans un pays allié. Lorsqu’’il se rend dans un territoire ouvertement en guerre avec son pays d’’origine ou qui conserve une position de neutralité, il n’’ajoute que la moitié de son niveau de prestige.');

ALTER TABLE profession ALTER COLUMN idProfession RESTART WITH 11;

-- Ajout de Compétences Spécifique
INSERT INTO competence_specifique (
    idCompetenceSpecifique, nom, description, codeCaracteristique, specialisation, prerequis, idProfession
) VALUES
      (1, 'Catalogue étendu',
       'Un artisan expérimenté garde en mémoire tout un catalogue de schémas. Lorsqu’un artisan a fini de mémoriser le plus de diagrammes possibles, il peut réaliser un jet de Catalogue étendu SD. 15 pour en retenir un de plus. Il n’existe pas de limite au nombre de schémas qu’il peut mémoriser, mais tous les 10 schémas, le SD augmente de +1.',
       'INT',
       'Maître artisan',
       'Aucun',
       1),
      (2, 'Compagnon',
       'Quand il commence à fabriquer un objet, l’artisan peut effectuer un jet de compagnon dont le SD est égal au SD de fabrication de l’objet. En cas de réussite, l’objet gagne +1 DÉG (pour les armes) ou +1 PA (pour les armures) par tranche de 2 points au-dessus du SD. La valeur de bonus ne peut pas excéder 5.',
       'TECH',
       'Maître artisan',
       'Catalogue étendu',
       1),
      (3, 'Maître compagnon',
       'Grâce à Maître compagnon, l’artisan réalise des armes d’excellente facture. Il peut réaliser à tout moment un jet de Maître compagnon dont le SD est égal au SD de fabrication de l’objet pour octroyer une résistance permanente de son choix à une armure, ou une valeur de saignement 50% ou d’écourdissement -2 à une arme (selon le type de dégât qu’elle inflige).',
       'TECH',
       'Maître artisan',
       'Compagnon',
       1);

ALTER TABLE competence_specifique ALTER COLUMN idCompetenceSpecifique RESTART WITH 4;
