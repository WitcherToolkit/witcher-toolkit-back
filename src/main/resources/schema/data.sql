
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
    (18, 'Pieds', 'Pieds', 'Cette caractéristique indique le nombre de dégâts non létaux que vous infligez avec un coup de poing.'),
    (19, 'Poings', 'Poings', 'Cette caractéristique indique le nombre de dégâts non létaux que vous infligez avec un coup de pied.');

ALTER TABLE caracteristique ALTER COLUMN idCaracteristique RESTART WITH 20;

-- Ajout de Compétences
INSERT INTO competence (idCompetence, nom, description, specialisation, prerequis, isExclusive, idCaracteristique) VALUES
    (1, 'Connaissance de la rue', 'Cette compétence ne concerne pas la géographie urbaine à proprement parler, mais plutôt le fonctionnement de cet environnement. Avec une base de 10 vous en savez suffisamment pour éviter les voyous et vous rendre dans les quartiers les plus sûrs de la ville. Avec une base de 13 vous pouvez en général déterminer quelle faction exerce son influence sur un quartier donné et expliquer la raison de cette domination. Avec une base de 16 vous pouvez récolter une quantité d’informations impressionnantes sur une zone rien qu’en l’observant, mais aussi identifier les personnalités importantes de la ville et leurs relations. Avec une base de 20 vous évaluez la situation en un clin d’œil. Vous en savez assez pour compter parmi les habitants.', '', '', false, 1 ),
    (2, 'Connaissance des monstres', 'La somme d’information que vous possédez à propos des monstres. Avec une base de 10 vous êtes capable de différencier un nekker d’une goule. Avec une base de 13 vous savez classer les monstres au sein des diverses catégories générales. Avec une base de 16 vous êtes au fait des différentes forces et faiblesses que possèdent les créatures les plus communes. Avec une base de 20 il est rare que vous rencontriez des monstres que vous seriez incapable d’analyser en quelques secondes, un exploit dont même un sorceleur serait fier.','Criminel', 'N/A', false, 1),
    (3, 'Déduction', 'La capacité à formuler des conclusions grâce aux indices dont vous disposez. Avec une base de 10 vos intuitions vous mettent en général sur la bonne piste. Avec une base de 13 vos ressentiments sont presque toujours fondés, vous pouvez même les étayer à l’aide de raisonnements logiques. Avec une base de 16 il vous suffit de quelques indices pour avoir ce qui s’est passé ou ce qui pourrait advenir. Avec une base de 20 vous ne vous trompez presque jamais dans vos déductions. Vous êtes capables de trouver des réponses même lorsque vous disposez de preuves partielles.','Criminel', 'Connaissance', false, 1 ),
    (4, 'Éducation', 'Reflète votre niveau d’instruction. Avec une base de 10 vos parents vous ont légué des connaissances sommaires sur la marche du monde. Avec une base de 13 vous avez probablement bénéficié des leçons d’un mentor ou eu la chance de fréquenter l’une des rares écoles ouvertes dans votre région natale. Avec une base de 16 vous avez acquis un savoir encyclopédique en allant par exemple étudier à Oxenfurt ou dans une autre académie du genre. Avec une base de 20 vous êtes un érudit capable de rivaliser d’intelligence avec des professeurs d’université et des mages siégeant aux conseils royaux.','', '', false, 1),
    (5, 'Enseignement', 'La capacité à dispenser son savoir. Vous n’êtes pas tenu de maîtriser cette capacité pour en enseigner d’autres, mais elle vous facilitera la tâche. Avec une base de 10 vous savez expliquer du début à la fin un procédé simple à votre élève, mais sans que ce dernier saisisse à coup sûr tous les tenants et aboutissants de votre cheminement. Avec une base de 13 vVous pouvez enseigner les bases d’une compétence à un élève attentif sans rencontrer de problème particulier. Avec une base de 16 si vous disposez de suffisamment de temps, vous pouvez apprendre des procédés plus complexes à vos étudiants, même s’ils ne sont guère attentifs. Avec une base de 20 vous êtes un maître pédagogue capable d’intéresser n’importe qui. Puisque vos leçons font toujours mouche du premier coup, vous avez rarement besoin de vous répéter.', '', '', false, 1 ),
	(6, 'Étiquette',  'La capacité à dispenser son savoir. Vous n’êtes pas tenu de maîtriser cette capacité pour en enseigner d’autres, mais elle vous facilitera la tâche. Avec une base de 10 vous savez expliquer du début à la fin un procédé simple à votre élève, mais sans que ce dernier saisisse à coup sûr tous les tenants et aboutissants de votre cheminement. Avec une base de 13 vous pouvez enseigner les bases d’une compétence à un élève attentif sans rencontrer de problème particulier. Avec une base de 16 si vous disposez de suffisamment de temps, vous pouvez apprendre des procédés plus complexes à vos étudiants, même s’ils ne sont guère attentifs. Avec une base de 20 vous êtes un maître pédagogue capable d’intéresser n’importe qui. Puisque vos leçons font toujours mouche du premier coup, vous avez rarement besoin de vous répéter.', '', '', false, 1 ),
	(7, 'Langue', 'La capacité à dispenser son savoir. Vous n’êtes pas tenu de maîtriser cette capacité pour en enseigner d’autres, mais elle vous facilitera la tâche. Avec une base de 10 vous savez expliquer du début à la fin un procédé simple à votre élève, mais sans que ce dernier saisisse à coup sûr tous les tenants et aboutissants de votre cheminement. Avec une base de 13 vous pouvez enseigner les bases d’une compétence à un élève attentif sans rencontrer de problème particulier. Avec une base de 16 si vous disposez de suffisamment de temps, vous pouvez apprendre des procédés plus complexes à vos étudiants, même s’ils ne sont guère attentifs. Avec une base de 20 vous êtes un maître pédagogue capable d’intéresser n’importe qui. Puisque vos leçons font toujours mouche du premier coup, vous avez rarement besoin de vous répéter.', '', '', false, 1 ),
	(8, 'Négoce', 'La capacité à dispenser son savoir. Vous n’êtes pas tenu de maîtriser cette capacité pour en enseigner d’autres, mais elle vous facilitera la tâche. Avec une base de 10 vous savez expliquer du début à la fin un procédé simple à votre élève, mais sans que ce dernier saisisse à coup sûr tous les tenants et aboutissants de votre cheminement. Avec une base de 13 vous pouvez enseigner les bases d’une compétence à un élève attentif sans rencontrer de problème particulier. Avec une base de 16 si vous disposez de suffisamment de temps, vous pouvez apprendre des procédés plus complexes à vos étudiants, même s’ils ne sont guère attentifs. Avec une base de 20 vous êtes un maître pédagogue capable d’intéresser n’importe qui. Puisque vos leçons font toujours mouche du premier coup, vous avez rarement besoin de vous répéter.' , '', '', false, 2),
	(9, 'Survie', 'La capacité à dispenser son savoir. Vous n’êtes pas tenu de maîtriser cette capacité pour en enseigner d’autres, mais elle vous facilitera la tâche. Avec une base de 10 vous savez expliquer du début à la fin un procédé simple à votre élève, mais sans que ce dernier saisisse à coup sûr tous les tenants et aboutissants de votre cheminement. Avec une base de 13 vous pouvez enseigner les bases d’une compétence à un élève attentif sans rencontrer de problème particulier. Avec une base de 16 si vous disposez de suffisamment de temps, vous pouvez apprendre des procédés plus complexes à vos étudiants, même s’ils ne sont guère attentifs. Avec une base de 20 vous êtes un maître pédagogue capable d’intéresser n’importe qui. Puisque vos leçons font toujours mouche du premier coup, vous avez rarement besoin de vous répéter.' , '', '', false, 2),
	(10, 'Tactique', 'La capacité à dispenser son savoir. Vous n’êtes pas tenu de maîtriser cette capacité pour en enseigner d’autres, mais elle vous facilitera la tâche. Avec une base de 10 vous savez expliquer du début à la fin un procédé simple à votre élève, mais sans que ce dernier saisisse à coup sûr tous les tenants et aboutissants de votre cheminement. Avec une base de 13 vous pouvez enseigner les bases d’une compétence à un élève attentif sans rencontrer de problème particulier. Avec une base de 16 si vous disposez de suffisamment de temps, vous pouvez apprendre des procédés plus complexes à vos étudiants, même s’ils ne sont guère attentifs. Avec une base de 20 vous êtes un maître pédagogue capable d’intéresser n’importe qui. Puisque vos leçons font toujours mouche du premier coup, vous avez rarement besoin de vous répéter.' , '', '', false, 2);

ALTER TABLE competence ALTER COLUMN idCompetence RESTART WITH 11;

-- Ajout de Professions
INSERT INTO profession (idProfession, nom, description, vigueur, maxSort, maxRituel, MAXENVOUTEMENT, maxInvocation) VALUES
    (1, 'Artisan', 'Un artisan talentueux peut effectuer des réparations de fortune sur une armure ou une arme pour qu’elle reste utilisable le temps du combat. Il pourra par exemple nouer deux parties d’une corde d’arc rompue, affûter la lame d’une épée ou clouer une plaque de métal sur un bouclier fendu. L’artisan consacre un tour à cette action. Il effectue alors un jet de rafistolage dont le SD est égal au SD de fabrication de l’objet -3. S’il réussit, l’armure brisée regagne la moitié de son PA total, ou la moitié de sa fiabilité dans le cas d’une épée ou d’un bouclier. Tant qu’elle n’est pas correctement réparée à l’aide d’une forge, une arme n’inflige que la moitié des dégâts normaux.', 0, 0, 0, 0, 0),
    (2, 'Barde', 'Le barde est un compagnon des plus précieux, surtout quand le groupe commence à manquer d''argent. Il peut réaliser un jet de prestation et se donner en spectacle sur la place de la ville durant une heure. Le résultat du jet correspond au montant qu’il a récolté en se produisant dans la rue. Un échec critique peut diminuer le résultat du jet, et si le total devient négatif, cela signifie que la barde n’a pas réussi à recueillir une seule pièce. Il se fait huer par les habitants pour sa piètre performance et subit un malus de -2 en charisme lorsqu’il interagit avec les citadins jusqu''à la fin de la journée.', 0, 0, 0, 0, 0),
    (3, 'Criminel', 'Qu''ils soient assassins, voleurs faussaires ou contrebandiers, tous les hors-la-loi ont en commun une paranoïa constante qui leur permet d’éviter les problèmes. Lorsqu’un criminel arrive à moins de 10m d''un piège (ce qui inclut les pièges expérimentaux, les chausses-trappes des hommes d''armes et les embuscades), il effectue immédiatement un jet de paranoïa exercée dont le SD est égal à celui requis pour déceler le piège, au résultat du jet de furtivité du groupe posté en embuscade ou au SD fixé par le MJ. Même s''il n''arrive pas à repérer le piège, il sentira au fond de lui-même que quelque-chose ne va pas.', 0, 0, 0, 0, 0),
    (4, 'Docteur', 'N’’importe qui est capable d’’appliquer un onguent ou de bander une blessure, mais seul un docteur a reçu la formation nécessaire pour accomplir des actes chirurgicaux d’’une grande complexité. Un docteur avec mains thérapeutiques est le seul personnage capable de soigner une blessure critique. Pour soigner une blessure critique, il doit réussir un nombre de jets de mains thérapeutiques qui dépend de la gravité de cette blessure. Le SD du jet est aussi basé sur ce paramètre. Les mains thérapeutiques peuvent aussi servir lors des actions de premiers soins.', 0, 0, 0, 0, 0),
    (5, 'Homme d''arme', 'Les véritables hommes d’’armes issus, par exemple, des Stries Bleues témériennes ou de la brigade Imprera de Nilfgaard sont des vétérans endurcis qui n’’abandonnent jamais et ne se rendent pas. Lorsque les points de santé d’’un homme d’’armes tombent à 0 ou moins, il peut effectuer un jet de dur à cuire dont le SD est égal au double de son score de santé négatif. S’’il échoue, il entre en état de mort imminente selon les règles habituelles. S’’il le réussit, il peut continuer à se battre comme s’’il avait atteint son seuil de blessures. Lorsqu’’il subit des dégâts, il réalise un nouveau jet dont le SD est calculé selon son nouveau score de santé.', 0, 0, 0, 0, 0),
    (6,'Mage','Pour devenir pleinement un mage, une personne sensible à la magie doit apprendre les bases de cet art au sein d’’une académie de magie. Un mage peut réaliser un jet d’exercice de la magie dès qu’il se trouve face à un phénomène magique ou un sort inconnu, ou encore lorsqu’il analyse une théorie de la magie. Le SD du jet est fixé par le MJ. En cas de réussite, le mage apprend tout ce qu’’il y a à savoir sur le phénomène en question. Un jet d’exercice de la magie peut aussi servir à détecter la magie en cours d’utilisation ou les spectres.', 5, 5, 11, 1, 0),
    (7, 'Marchand', 'Un marchand ordinaire gagne sa vie grâce à son échope qui attire les clients alentours. En revanche, un commerçant itinérant part à la rencontre de ses clients. Il parcourt les routes du monde entier, ce qui lui permet d’’en apprendre beaucoup sur les peuples qu’’il croise. Un marchand peut effectuer un jet de grand voyageur chaque fois qu’’il désire connaître une anecdote concernant un objet, une culture ou une région particulière. Le MJ fixe le SD du jet. Si ce dernier réussit, le marchand se rappelle la réponse à la question posée grâce aux souvenirs datant de son dernier voyage dans le lieu concerné.', 0, 0, 0, 0, 0),
    (8, 'Prêtre', 'Les temples du monde entier sont bien souvent des lieux chaleureux qui aident les fidèles et accueillent les nouveaux convertis. Un prêtre peut réaliser un jet d’initié des dieux dont le SD est fixé par les MJ dans les églises de cette religion pour obtenir un hébergement gratuit, des soins et d’autres services, à la discrétion du MJ. L’initiation des dieux fonctionne aussi auprès des croyants de la même confession, bien qu’ils aient moins à offrir qu’un temple. N’oubliez pas qu’initié des dieux ne fonctionne jamais avec les membres d’une autre religion.', 2, 0, 2, 2, 2),
    (9, 'Sorceleur', 'Les sorceleurs passent les premières années de leur vie confinés entre les murs de leur forteresse, où ils étudient d’énormes tomes poussiéreux et suivent un entraînement martial démentiel. De nombreux savants ont démontré que les meilleures armes d’un sorceleur étaient sa connaissance des monstres ainsi que ses capacités d’’adaptation. Il peut donc diminuer les pénalités provoquées par un environnement hostile ou un relief accidenté d’’une valeur égale à la moitié de son score de formation de sorceleur (minimum 1). Vous pouvez également utiliser formation de sorceleur dans les situations requérant l’’usage de la compétence connaissance des monstres.', 2, 0, 0, 0, 0),
    (10, 'Noble', 'Qu’’il soit noble de naissance ou qu’’il ait acquis ses titres en accomplissant des faits d’’armes, l’’aristocrate affiche son statut privilégié afin que nul ne l’ignore. Les paysans peuvent bien maudire les familles nobles et les ridiculiser à l’abri dans leurs chaumières, mais aucun d’’entre eux n’’oserait les insulter en face. Un noble ajoute son niveau de prestige à son score de réputation lorsqu’’il se trouve sur sa terre natale ou dans un pays allié. Lorsqu’’il se rend dans un territoire ouvertement en guerre avec son pays d’’origine ou qui conserve une position de neutralité, il n’’ajoute que la moitié de son niveau de prestige.', 0, 0, 0, 0, 0);

ALTER TABLE profession ALTER COLUMN idProfession RESTART WITH 11;

-- Ajout de l'inventaire wiki
INSERT INTO inventaireWiki (idInventaireWiki, quantite, nom, type, effet, isSpecial, idProfession) VALUES
    ( 1, '50', 'couronnes de composants', '', '', FALSE, 1),
    ( 2, '1', 'ensemble d''alchimie', '', '', FALSE, 1),
    ( 3, '1', 'épée longue de fer', 'arme', '', FALSE, 1),
    ( 4, '1', 'forge portable', '', '', FALSE, 1),
    ( 5, '1', 'masse d''armes', 'arme', '', FALSE, 1),
    ( 6, '1', 'outils d''artisan', '', '', FALSE, 1),
    ( 7, '1', 'outils de marchand', '', '', FALSE, 1),
    ( 8, '1', 'petit coffre', '', '', FALSE, 1),
    ( 9, '1', 'sablier', '', '', FALSE, 1),
    ( 10, '1', 'serrure', '', '', FALSE, 1),
    ( 11, '1', 'bourse', '', '', FALSE, 2),
    ( 12, '1', 'dague', 'arme', '', FALSE, 2),
    ( 13, '1', 'flasque d''alcool', '', '', FALSE, 2),
    ( 14, '1', 'fourreau de jarretière', '', '', FALSE, 2),
    ( 15, '1', 'jeu de qwynt', '', '', FALSE, 2),
    ( 16, '1', 'journal cadenassé', '', '', FALSE, 2),
    ( 17, '1', 'miroir de poche', '', '', FALSE, 2),
    ( 18, '1', 'parfum/eau de toilette', '', '', FALSE, 2),
    ( 19, '1', 'piste de dé', '', '', FALSE, 2),
    ( 20, '1', 'un instrument', '', '', FALSE, 2),
    ( 21, '1', 'chloroforme', '', '', FALSE, 3),
    ( 22, '1', 'coup-de-poind', '', '', FALSE, 3),
    ( 23, '5', 'coureaux de lancer', 'arme', '', FALSE, 3),
    ( 24, '1', 'dés pipés', '', '', FALSE, 3),
    ( 25, '1', 'fourreaux de manche', '', '', FALSE, 3),
    ( 26, '1', 'lanterne sourde', '', '', FALSE, 3),
    ( 27, '1', 'outils de voleur', '', '', FALSE, 3),
    ( 28, '1', 'sacoche', '', '', FALSE, 3),
    ( 29, '1', 'stylet', 'arme', '', FALSE, 3),
    ( 31, '10', 'bougies', '', '', FALSE, 4),
    ( 32, '1', 'couverture', '', '', FALSE, 4),
    ( 33, '1', 'dague', 'arme', '', FALSE, 4),
    ( 34, '10', 'fluide de sterilisation', '', '', FALSE, 4),
    ( 35, '1', 'grande tente', '', '', FALSE, 4),
    ( 36, '10', 'herbes engourdissantes', '', '', FALSE, 4),
    ( 37, '1', 'instruments chirurgicaux', '', '', FALSE, 4),
    ( 38, '1', 'necessaire d''écriture', '', '', FALSE, 4),
    ( 39, '10', 'poudre de coagulation', '', '', FALSE, 4),
    ( 40, '1', 'sablier', '', '', FALSE, 4),
    ( 41, '1', 'arbalète + 20 carreaux', 'arme', '', FALSE, 5),
    ( 42, '1', 'boble d''acier', 'bouclier', '', FALSE, 5),
    ( 43, '1', 'brigandine', 'armure', '', FALSE, 5),
    ( 44, '1', 'camail', '', '', FALSE, 5),
    ( 45, '5', 'couteaux de lancer', 'arme', '', FALSE, 5),
    ( 46, '1', 'hache de bataille', 'arme', '', FALSE, 5),
    ( 47, '1', 'Kord', 'arme', '', FALSE, 5),
    ( 48, '1', 'lance', 'lance', '', FALSE, 5),
    ( 49, '1', 'pantalon renforcé', 'armure', '', FALSE, 5),
    ( 50, '1', 'sacoche', '', '', FALSE, 5),
    ( 51, '100', 'couronnes de composants', '', '', FALSE, 6),
    ( 52, '1', 'bâton', 'arme', '', FALSE, 6),
    ( 53, '1', 'bourse', '', '', FALSE, 6),
    ( 54, '1', 'dague', 'arme', '', FALSE, 6),
    ( 55, '1', 'fourreau de jarretière', '', '', FALSE, 6),
    ( 56, '1', 'journal', '', '', FALSE, 6),
    ( 57, '1', 'miroir de poche', '', '', FALSE, 6),
    ( 58, '1', 'necessaire d''écriture', '', '', FALSE, 6),
    ( 59, '1', 'sablier', '', '', FALSE, 6),
    ( 60, '1', 'trousse de maquillage', '', '', FALSE, 6),
    ( 61, '1', 'arbalète + 20 carreaux', 'arme', '', FALSE, 7),
    ( 62, '1', 'dague', '', '', FALSE, 7),
    ( 63, '1', 'grande tente', '', '', FALSE, 7),
    ( 64, '1', 'journal', '', '', FALSE, 7),
    ( 65, '1', 'nécessaire d''écriture', '', '', FALSE, 7),
    ( 66, '1', 'outils de marchand', '', '', FALSE, 7),
    ( 67, '100', 'couronnes de composants', '', '', FALSE, 8),
    ( 68, '1', 'bâton', 'arme', '', FALSE, 8),
    ( 69, '1', 'dague', 'arme', '', FALSE, 8),
    ( 70, '1', 'ensemble d''alchimie', '', '', FALSE, 8),
    ( 71, '5', 'fluide stérilisant', '', '', FALSE, 8),
    ( 72, '5', 'herbes engourdissantes', '', '', FALSE, 8),
    ( 73, '1', 'instruments chirurgicaux', '', '', FALSE, 8),
    ( 74, '5', 'poudre de coagulation', '', '', FALSE, 8),
    ( 75, '1', 'sablier', '', '', FALSE, 8),
    ( 76, '1', 'symbole sacré', '', '', FALSE, 8),
    ( 77, '1', 'arbalète de poing', 'arme', '', FALSE, 9),
    ( 78, '5', 'couteaux de lancé', 'arme', '', FALSE, 9),
    ( 79, '1', 'ensemble d''alchimie', '', '', FALSE, 9),
    ( 80, '1', 'gambison à tissage rembourré', 'armure', '', FALSE, 9),
    ( 81, '1', 'cheval', '', '', FALSE, 9),
    ( 82, '1', 'épée de sorceleur en acier', '', '', TRUE, 9),
    ( 83, '1', 'épée de sorceleur en argent', '', '', TRUE, 9),
    ( 84, '1', 'formule de décoction', '', '', TRUE, 9),
    ( 85, '2', 'formule d''huile', '', '', TRUE, 9),
    ( 86, '2', 'formule de potion', '', '', TRUE, 9),
    ( 87, '1', 'médaillon de sorceleur', '', '', TRUE, 9),
    ( 88, '1', 'bijou', '', '', FALSE, 10),
    ( 89, '1', 'cheval', '', '', FALSE, 10),
    ( 90, '1', 'eau de toilette', '', '', FALSE, 10),
    ( 91, '1', 'encre invisible', '', '', FALSE, 10),
    ( 92, '1', 'esboda', 'arme', '', FALSE, 10),
    ( 93, '1', 'journal cadenassé', '', '', FALSE, 10),
    ( 94, '1', 'nécessaire d''écriture', '', '', FALSE, 10),
    ( 95, '1', 'poche secrète', '', '', FALSE, 10),
    ( 96, '1', 'trousse de maquillage', '', '', FALSE, 10),
    ( 97, '1', 'vêtements à la mode', '', '', FALSE, 10);

ALTER TABLE inventaireWiki ALTER COLUMN idInventaireWiki RESTART WITH 98;

INSERT INTO competenceProfession(idComptetenceProfession, idProfession, idCompetence) VALUES
    (1,1,1),
    (2,1,2),
    (3,1,3),
    (4,2,4),
    (5,2,5),
    (6,2,6),
    (7,3,7),
    (8,3,8),
    (9,3,9),
    (10,4,10),
    (11,4,1),
    (12,4,2),
    (13,5,3),
    (14,5,4),
    (15,5,5),
    (16,6,6),
    (17,6,7),
    (18,6,8),
    (19,7,9),
    (20,7,10),
    (21,7,1),
    (22,8,2),
    (23,8,3),
    (24,8,4),
    (25,9,5),
    (26,9,6),
    (27,9,7),
    (28,10,8),
    (29,10,9),
    (30,10,10);

ALTER TABLE competenceProfession ALTER COLUMN idComptetenceProfession RESTART WITH 30;

-- Ajout d'envoûtement
INSERT INTO envoutement (idEnvoutement, nom, cout, effet, prerequis, danger)
VALUES
    (1, 'L''envoûtement des Ombres', '4', 'L''envoûtement des ombres crée des murmures dans les ténèbres et des silhouettes dans les coins. Le sujet doit faire des jets de vigilance au hasard avec un SD non spécifié, apercevant toujours quelque chose ou quelqu''un du coin de l’œil. Les jets de vigilance ne sont jamais pour une menace réelle, juste des visions.  Le sujet doit apporter un bol d''eau pure, une branche de myrte blanc et une bouteille d''encre dans une clairière lors d’une lune croissante. Quand la lune est au plus haut, le sujet doit verser l’encre dans l’eau, tremper la branche dans le mélange et asperger des gouttelettes en cercle autour de lui tout en retenant sa respiration.', 'Un bol d''eau pure, une branche de myrte blanc, une bouteille d''encre, une clairière, une lune croissante', 'Faible'),
    (2, 'La démangeaison éternelle', '4', 'La démangeaison éternelle fait pousser des pustules qui grattent et brûlent sur les parties intimes du sujet. La démangeaison n''inflige pas de dégâts, mais dérange constamment, ce qui inflige un malus de -1 à toutes les tâches. En plus du -1, la cible a un malus de -5 en séduction une fois dans "la chambre à coucher".    Le sujet doit rassembler 1 dose de sclérodermie, de petite ciguë et de bryone. Il doit allumer un feu de camp et faire un bouquet avec les herbes. Quand tout est prêt, le sujet doit embraser les herbes et effriter les cendres chaudes sur la zone atteinte tout en récitant une série de mots magiques.', '1 dose de sclérodermie, 1 dose de petite ciguë, 1 dose de bryone, un feu de camp', 'Faible'),
    (3, 'La chance démoniaque', '8', 'La chance démoniaque tourmente le sujet avec des touches de mauvaise fortune. Dans des situations très stressantes, comme un combat ou en effectuant une tâche avec un délai très court ou un SD supérieur à 15, le sujet fait un échec critique sur un 1 ou 2 naturel.', 'Un clou en argent, 2 doses d''aconit, une mèche de cheveux d''une vierge', 'Modéré'),
    (4, 'Le cauchemar', '8', 'Le cauchemar fait revivre au sujet le même cauchemar horrifique.Chaque nuit, le sujet doit faire un jet de résistance à la contrainte avec un SD égal au jet d''envoûtement du lanceur pour lancer cet envoûtement. En cas de réussite, le sujet parvient à dormir une nuit complète, bien qu''elle ne soit pas reposante. En cas d’échec, il dort à peine et ne régénère pas d''END ou de PS durant la nuit. S’il échoue trois nuits d’affilée, il réduit de moitié son endurance et a un malus de -2 à toutes ses actions jusqu’à pouvoir faire une nuit complète.', '5 bougies, 5 os de bête, 1 minerai luisant', 'Modéré'),
    (5, 'Le baiser de Pesta', '12', 'Le baiser de Pesta ôte au sujet sa capacité de combattre les maladies et sa résistance à la nausée. À chaque fois que le sujet est en contact avec une personne malade, il a 75% de chance d’attraper la maladie. À chaque fois que il sent quelque chose de nauséabond, même légèrement, il doit réussir un jet de résilience avec un SD 16 ou être nauséeux.', '3 doses d''argile alluviale, 1 dose de charbon, 3 doses de résine, 1 dose de poussière imprégnée', 'Élevé'),
    (6, 'L''envoûtement de la bête', '12', 'L''envoûtement de la bête rend la cible répugnante aux yeux des animaux et des bêtes. Dès que la cible s’approche d’un animal à moins de 10m, ce dernier réagira mal au sujet, ce qui lui inflige un malus de -3 en survie en ce qui concerne le dressage des animaux. À chaque fois que le sujet s’approche d’un animal à moins de 10m, il y a 50% de chance pour que l’animal attaque.', 'Un petit animal vivant, 2 doses de gui, une dose de phosphore, 2 doses d''œil de corbeau, 3 doses de racine de mandragore', 'Élevé');

ALTER TABLE envoutement ALTER COLUMN idEnvoutement RESTART WITH 7;

-- Ajout de la Magie
INSERT INTO magie (idMagie, nom, cout, effet, portee, duree, nature, niveau, contre, type) VALUES
    ('1', 'Compas magique', '3 END', 'Le compas magique permet de déterminer instantanément la direction vers un endroit où vous êtes déjà allée auparavant.inon, le sort indique le nord.', 'Personnelle', '1D6 heure', 'Mixte', 'Sorts de novice', '', 'Sort'),
    ('2', 'Dissipation', 'Variable', 'Dissipation met fin à un sort, un rituel ou une malédiction en cours dans sa portée. Ce sort permet d’abbuler une magie qui dure et peut être utilisé comme une action défensive pour bloquer une attaque magique avec ou sans composants. Pour annuler un effet magique, vous devez dépenser la moitié des points de Résilience utilisés par le lanceur et réussir un jet d’Incantation en opposition à son jet pour lancer le sort.', '10 m', 'Instantané', 'Mixte', 'Sorts de novice', 'Incantation', 'Sort'),
    ('3', 'Glamour', '5 END', 'Glamour vous permet de mettre une illusion autour de vous qui vous fait paraître éblouissant. Ce sort vous donne un bonus de +3 en Séduction, Charisme et Commandement.', 'Personnelle', '1D6 heure', 'Mixte', 'Sorts de novice', '', 'Sort'),
    ('4', 'Invocation de bâton', '2 END', 'Invocation de bâton vous permet de dématérialiser votre bâton et de le téléporter à un endroit où vous avez été présent au cours du dernier jour. Vous pouvez lancer à nouveau le sort pour faire revenir le bâton à vous.', '', 'Instantané', 'Mixte', 'Sorts de novice', '', 'Sort'),
    ('5', 'Manipulation de l''esprit', '3 END', 'Manipulation de l’esprit vous permet de forcer votre cible à ressentir l’une de ces émotions pendant la durée du sort : haine, amour, dépression ou euphorie.', '5 m', '1D10 rounds', 'Mixte', 'Sorts de novice', 'Résistance à la magie', 'Sort'),
	('6', 'Miroir d''Afan', '3 END', 'Inventé par le talentueux magicien aedirnien Afan de Guletta, le miroir d’Afan crée 1D10 copies illusoires du lanceur. Ces popies sont intangibles, mais impossibles à différencier du lanceur, qui les contrôle par l’esprit. Contrôler ces copies ne demande pas d’action, mais elles ne peuvent pas se déplacer au-delà de la portée du dort.', '10 m', 'Actif (2 END)', 'Mixte', 'Sorts de novice', '', 'Sort'),
    ('7', 'Poussière aveuglante', '3 END', 'La poussière aveuglante vous permet de lancer une poussière magique dans les yeux d’une cible afin de l’aveugler pour la durée du sort.', '4 m', '1D10 rounds', 'Mixte', 'Sorts de novice', 'Esquive ou Blocage', 'Sort'),
    ('8', 'Télépathie', '2 END', 'Télépathie vous permet de communiquer par télépathie avec une personne pendant la durée du sort, sans barrière de langage.', '10 m', 'Actif (1 END)', 'Mixte', 'Sorts de novice', '', 'Sort'),
    ('9', 'Cenlly Graig', '3 END', 'Cenlly Graig lance des pierres acérées sur votre adversaire. Lors de votre jet, pour chaque point qui dépasse la défense de votre adversaire (maximum 10), vous infligez 1D6 points de dégâts. Chaque lancer compte comme une attaque à part entière.', '5 m', 'Instantané', 'Terre', 'Sorts de novice', 'Esquive ou Blocage', 'Sort'),
    ('10', 'Codi Bywyd', '2 END', 'Codi Bywyd permet de faire parvenir à maturité une graine en l’espace d’un round.Cela permet de faire pousser des herbes et des plantes alchimiques, mais rien de plus grand comme un arbre.', '4 m', 'Instantané', 'Terre', 'Sorts de novice', '', 'Sort'),
    ('11', 'Diagnostic', '3 END', 'Diagnostic permet d’évaluer rapidement la santé d’une personne et de déterminer combien de points de santé elle a, ses blessures et si elle est malade ou empoisonnée.', '5 m', 'Instantané', 'Terre', 'Sorts de novice', '', 'Sort'),
    ('12', 'Pic terrestre', '5 END', 'Pic terrestre crée une stalagmite inclinée de manière à pouvoir poignarder une cible. Le pic inflige 5D6 points de dégâts et reste en place jusqu’à être détruit. Il peut être détruit en lui infligeant 20 points de dégâts.', '6 m', 'Instantané', 'Terre', 'Sorts de novice', 'Esquive ou Blocage', 'Sort'),
    ('13', 'Plume de Luthien', '2 END', 'Nommé ainsi d’après son créateur, Luthien de Ebbing, Plume de Luthien peut graver des écritures ou des dessins sur n’importe quelle surface solide. Le sort ne peut pas être utilisé sur une créature vivante.', '1 m', 'Instantané', 'Terre', 'Sorts de novice', '', 'Sort'),
    ('14', 'Prison de Talfryn', '3 END', 'Prison de Talfryn tient son nom de Talfryn de Nazair, un chevalier traître qui a été piégé trois jours durant dans le jardin du magicien Drystan. Le sort permet d’empêcher une cible dans des racines. Il faut 15 points de dégâts pour détruire les racines. Autrement, pour s’en échapper, il faut un jet d’esquive ou d’évasion avec un SD égal à votre résultat pour lancer le sort.', '10 m', 'Jusqu''à destruction', 'Terre', 'Sorts de novice', 'Esquive', 'Sort'),
	('15', 'Soins magiques', '5 END', 'Soins magique stimule la régénération naturelle d’une cible en lui soignant 3 points de dégâts par round. L’effet dure pendant la durée du sort. Sinon, ce sort peut être utilisé plusieurs reprises pour soigner une blessure critique.', '2 m', '1D10 rounds', 'Terre', 'Sorts de novice', '', 'Sort'),
	('16', 'Soufle de Korath', '2 END', 'Souffle de Korath démolit une surface de terre ou de pierre proche et pulvérise du sable brûlant dans un cône de 3m devant le lanceur. Les adversaires dans cette zone qui ratent leur défense sont aveuglés pour 1D6 rounds.', 'Cône de 3 m', 'Instantané', 'Terre', 'Sorts de novice', 'Esquive ou Blocage', 'Sort'),
	('17', 'Abri d''Urien', '3 END', 'Apple pie tart cotton candy toffee jujubes cheesecake. Candy shortbread fruitcake jelly beans soufflé dragée. Marshmallow wafer halvah cookie jelly-o toffee chocolate bar biscuit gummi bears. Tiramisu pie tiramisu pastry apple pie. Chocolate brownie marzipan jujubes jelly beans. Candy biscuit danish tart cupcake jujubes.', 'Rayon de 8 m', '1D6 heure', 'Air', 'Sorts de novice', '', 'Sort'),
    ('18', 'Adenydd', '4 END', 'Ice cream wafer tart chocolate cake dragée. Jelly-o soufflé danish pastry cheesecake chocolate. Toffee chocolate candy canes muffin muffin tootsie roll macaroon macaroon.', 'Personnelle', 'Actif (2 END)', 'Air', 'Sorts de novice', '', 'Sort'),
    ('19', 'Air frais', '2 END', 'Bonbon pudding lollipop bear claw liquorice powder. Chocolate bar macaroon jelly beans pie chocolate bar croissant pie icing icing. Soufflé powder tart jelly beans gummies bear claw chupa chups bear claw tiramisu. Pastry chocolate cake jelly-o chupa chups biscuit icing.', 'Rayon de 4 m', 'Actif (2 END)', 'Air', 'Sorts de novice', '', 'Sort'),
    ('20', 'Poche d''air', '3 END', 'Apple pie topping sweet roll shortbread pudding marzipan tootsie roll carrot cake jujubes. Biscuit gingerbread apple pie halvah brownie shortbread sugar plum croissant. Pie toffee shortbread cake pie.', '12 m', '2D10 rounds', 'Air', 'Sorts de novice', '', 'Sort'),
	('21', 'Rafale de Bronwyn', '2 END', 'Oat cake dessert gingerbread halvah chupa chups tootsie roll. Oat cake jelly chocolate cake gummi bears sweet powder lollipop pie. Halvah tootsie roll bonbon halvah chupa chups topping muffin oat cake marzipan.', '2 m', 'Instantané', 'Air', 'Sorts de novice', 'Esquive', 'Sort'),
	('22', 'Télékinésie', '3 END', 'Marshmallow sesame snaps tootsie roll sweet cake cookie marzipan cookie dragée. Chocolate cake tootsie roll fruitcake brownie lemon drops macaroon bonbon. Cupcake tootsie roll topping cake marzipan sugar plum cookie.', '5 m', 'Actif (2 END)', 'Air', 'Sorts de novice', '', 'Sort'),
	('23', 'Tempête statique', '5 END', 'Ice cream candy cotton candy soufflé ice cream toffee jelly topping. Marzipan dessert cake jelly cotton candy. Bear claw bonbon cheesecake sweet cotton candy.', 'Rayon de 5 m', '2D6 rounds', 'Air', 'Sorts de novice', '', 'Sort'),
	('24', 'Zéphir', '5 END', 'Brownie marzipan tootsie roll candy pastry fruitcake icing powder cheesecake. Sesame snaps toffee brownie lollipop bear claw. Lemon drops pastry jelly dragée sugar plum bonbon sweet roll cotton candy. Carrot cake cookie cotton candy cupcake danish dragée cake fruitcake.', 'Rayon de 2 m', 'Instantané', 'Air', 'Sorts de novice', '', 'Sort'),
	('25', 'Aenye', '5 END', 'Liquorice wafer tiramisu cake croissant chupa chups ice cream. Candy icing donut halvah donut icing macaroon. Chocolate bar gummies croissant icing sweet tart.', '12 m', 'Instantané', 'Feu', 'Sorts de novice', 'Esquive ou Blocage', 'Sort'),
	('26', 'Aine Verseos', '1 END', 'Sweet biscuit gingerbread jelly jelly beans jelly beans sugar plum brownie cake. Topping gummi bears bear claw liquorice soufflé donut bear claw chocolate cotton candy. Chocolate cake caramels jelly sugar plum oat cake lollipop fruitcake.', '4 m', 'Actif (2 END)', 'Feu', 'Sorts de novice', '', 'Sort'),
	('27', 'Attiser les flammes', '3 END', 'Cotton candy cotton candy wafer ice cream jelly beans lollipop cookie brownie. Oat cake jelly-o sweet bonbon gummies cheesecake. Muffin jelly-o gummies lemon drops tart shortbread pie cotton candy. Jelly beans pie marshmallow marzipan candy.', '10 m', 'Actif (2 END)', 'Feu', 'Sorts de novice', '', 'Sort'),
	('28', 'Éclat magique', '2 END', 'Tootsie roll halvah apple pie danish danish brownie. Caramels wafer sweet roll pie tiramisu fruitcake. Cheesecake marzipan marzipan bonbon sesame snaps bonbon croissant. Soufflé bear claw carrot cake ice cream apple pie.', '8 m', 'Instantané', 'Feu', 'Sorts de novice', 'Résistance à la magie', 'Sort'),
	('29', 'Marque incandescente', '4 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '8 m', 'Instantané', 'Feu', 'Sorts de novice', 'Bouclier magique', 'Sort'),
	('30', 'Poigne de Cadfan', '4 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '8 m', '1D6 rounds', 'Feu', 'Sorts de novice', '', 'Sort'),
	('31', 'Tanio Ilchar', '3 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '8 m', 'Instantané', 'Feu', 'Sorts de novice', 'Esquive', 'Sort'),
	('32', 'Vague de feu', '4 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '3 m', 'Instantané', 'Feu', 'Sorts de novice', 'Esquive ou Blocage', 'Sort'),
	('33', 'Aire de glace', '2 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', '2D10 rounds', 'Eau', 'Sorts de novice', 'Esquive', 'Sort'),
	('34', 'Averse', '2 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '8 m', 'Actif (2 END)', 'Eau', 'Sorts de novice', '', 'Sort'),
	('35', 'Brouillard de Dormyn', '3 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', 'Actif (2 END)', 'Eau', 'Sorts de novice', '', 'Sort'),
	('36', 'Contrôle de l''eau', '5 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', 'Actif (2 END)', 'Eau', 'Sorts de novice', 'SD déterminé par le MJ', 'Signe'),
    ('37', 'Grêle de Cary', '3 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '5 m', 'Instantané', 'Eau', 'Sorts de novice', 'Esquive ou Blocage', 'Signe'),
    ('38', 'Malédiction de Sedna', '5 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '12 m', 'Actif (2 END)', 'Eau', 'Sorts de novice', 'Esquive', 'Signe'),
    ('39', 'Puro Dwr', '2 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '4 m', '1D10 rounds', 'Eau', 'Sorts de novice', '', 'Signe'),
    ('40', 'Rhewi', '2 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '8 m', '1D10 rounds', 'Eau', 'Sorts de novice', 'Esquive', 'Signe'),
    ('41', 'Illusion', '8 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '20 m', 'Actif (4 END)', 'Mixte', 'Sorts de compagnon', 'Résistance à la magie', 'Signe'),
    ('42', 'Technique d''Eilhart', '12 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '3 m', 'Instantané', 'Mixte', 'Sorts de compagnon', 'Résistance à la magie', 'Signe'),
	('43', 'Téléportation', '10 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '', 'Instantané', 'Mixte', 'Sorts de compagnon', '', 'Signe'),
	('44', 'Rhwystr Graig', '15 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '20 m', 'Jusqu''à destruction', 'Terre', 'Sorts de compagnon', '', 'Signe'),
    ('45', 'Séisme de Stammelford', '12 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '30 m', '1D10 rounds', 'Terre', 'Sorts de compagnon', 'Esquive', 'Signe'),
    ('46', 'Théorie d''Elgan', '10 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '8 m', '2D10 rounds', 'Terre', 'Sorts de compagnon', '', 'Signe'),
	('47', 'Gwynt Troelli', '12 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', 'Rayon de 10 m', 'Actif (4 END)', 'Air', 'Sorts de compagnon', '', 'Signe'),
	('48', 'Suffocation', '14 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', 'Actif (4 END)', 'Air', 'Sorts de compagnon', 'Résistance à la magie', 'Signe'),
	('49', 'Tonnerre d''Alzur', '15 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '25 m', 'Instantané', 'Air', 'Sorts de compagnon', 'Esquive', 'Signe'),
    ('50', 'Afflux de Demetia', '12 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', 'Actif (4 END)', 'Feu', 'Sorts de compagnon', '', 'Signe'),
    ('51', 'Prise de Seirff', '10 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', '2D10 rounds', 'Feu', 'Sorts de compagnon', 'Esquive', 'Invocation'),
    ('52', 'Vortex enflammé', '15 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', 'Actif (4 END)', 'Feu', 'Sorts de compagnon', 'Esquive', 'Invocation'),
    ('53', 'Anialwch', '8 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', 'Instantané', 'Eau', 'Sorts de compagnon', 'Résistance à la magie', 'Invocation'),
    ('54', 'Grêle dévastatrice de Merigold', '15 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '30 m', 'Actif (4 END)', 'Eau', 'Sorts de compagnon', 'Esquive', 'Invocation'),
    ('55', 'Vague de Naglfar', '10 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', 'Rayon de 3 m', 'Instantané', 'Eau', 'Sorts de compagnon', 'Esquive ou Blocage', 'Invocation'),
    ('56', 'Commandement psychique', '25 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', 'Jusqu''à la fin de la tâche', 'Mixte', 'Sorts de maître', 'Résistance à la magie', 'Invocation'),
	('57', 'Portail', '22 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '10 m', 'Actif (6 END)', 'Mixte', 'Sorts de maître', '', 'Invocation'),
	('58', 'Polymorphisme', '22 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', 'Personnelle', 'Jusqu''au nouveau lancement du sort', 'Terre', 'Sorts de maître', '', 'Invocation'),
    ('59', 'Transmutation', '25 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '2 m', 'Permanent', 'Terre', 'Sorts de maître', '', 'Invocation'),
    ('60', 'Derviche', '22 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', 'Rayon de 2 m', 'Actif (6 END)', 'Air', 'Sorts de maître', 'Esquive', 'Invocation'),
    ('61', 'Orage', '25 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '20 m', 'Actif (6 END)', 'Air', 'Sorts de maître', 'Esquive', 'Invocation'),
    ('62', 'Effet mirroir', '25 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '20 m', '2D6 rounds', 'Feu', 'Sorts de maître', 'Esquive ou Blocage', 'Invocation'),
    ('63', 'Feu de Melgar', '25 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', 'Rayon de 40 m', '2D6 rounds', 'Feu', 'Sorts de maître', 'Esquive ou Blocage', 'Invocation'),
    ('64', 'Séparation des eaux', '25 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', 'Rayon de 10 m', 'Actif (6 END)', 'Eau', 'Sorts de maître', '', 'Invocation'),
    ('65', 'Tryferi Gaeaf', '22 END', 'Donut gummies chocolate topping brownie apple pie. Chupa chups wafer biscuit marshmallow dessert macaroon chocolate bear claw. Fruitcake jelly-o apple pie bonbon macaroon sweet roll ice cream lemon drops chupa chups.', '20 m', '1D10 rounds', 'Eau', 'Sorts de maître', 'Esquive ou Blocage', 'Invocation');

ALTER TABLE magie ALTER COLUMN idMagie RESTART WITH 66;

-- Ajout des Rituels
INSERT INTO rituel (idRituel, nom, cout, effet, tempsPreparation, sd, duree, composant, niveau)VALUES
   (1, 'Hydromancie', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité d''eau ou un bol d''eau, 1 éclar de lune, 1 goutte de sang', 'novice'),
   (2, 'Pyromancie', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité de feu ou un bol de feu, 1 éclar de lune, 1 goutte de sang', 'novice'),
   (3, 'Terre et Pierre', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité de terre ou un bol de terre, 1 éclar de lune, 1 goutte de sang', 'novice'),
   (4, 'Aérien', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité d''air ou un bol d''air, 1 éclar de lune, 1 goutte de sang', 'novice'),
   (5, 'Mélange', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité de mélange ou un bol de mélange, 1 éclar de lune, 1 goutte de sang', 'novice'),
   (6, 'Eau et Feu', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt diurnal anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité d''eau et de feu ou un bol d''eau et de feu, 1 éclar de lune, 1 goutte de sang', 'novice'),
   (7, 'Terre et Air', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité de terre et d''air ou un bol de terre et d''air, 1 éclar de lune, 1 goutte de sang', 'compagnon'),
   (8, 'Eau et Terre', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité d''eau et de terre ou un bol d''eau et de terre, 1 éclar de lune, 1 goutte de sang', 'compagnon'),
   (9, 'Feu et Air', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité de feu et d''air ou un bol de feu et d''air, 1 éclar de lune, 1 goutte de sang', 'Maître'),
   (10, 'Eau et Air', '5 END', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '5 rounds', '15 (18)', 'actif (2END)', 'une petite quantité d''eau et d''air ou un bol d''eau et d''air, 1 éclar de lune, 1 goutte de sang', 'Maître');

ALTER TABLE rituel ALTER COLUMN idRituel RESTART WITH 11;

-- Ajout des Races
INSERT INTO race (idRace, nom) VALUES
    (1, 'Humain'),
    (2, 'Nain'),
    (3, 'Elfe'),
    (4, 'Halfelin'),
    (5, 'Sorceleur');

ALTER TABLE race ALTER COLUMN idRace RESTART WITH 6;

-- Ajout des particularités
INSERT INTO particularite (idParticularite, nom, description, idRace) VALUES
(1, 'Sens accrus', 'Les sorceleurs ont des sens plus développés que la moyenne des humains.', 5),
(2, 'Mutation durable', 'Les sorceleurs ont subi des mutations qui les rendent différents des autres humains.', 5),
(3, 'Sensibilité émoussée', 'Les sorceleurs ont une sensibilité émoussée par rapport aux autres humains.', 5),
(4, 'Réflexes hors du commun', 'Les sorceleurs ont des réflexes hors du commun.', 5),

(5, 'Esthète', 'Les elfes sont des êtres sensibles à la création artistique.', 3),
(6, 'Oeil d''aigle', 'Les elfes ont une vue perçante.', 3),
(7, 'Harmonie avec la nature', 'Les elfes ont une affinité particulière avec la nature.', 3),

(8, 'Tanné comme le cuir', 'Les nains sont réputés pour leur résistance physique.', 2),
(9, 'Coriace', 'Les nains sont coriace.', 2),
(10, 'Oeil de l''expert', 'Les nains repèrent facilement les petits détails.', 2),

(11, 'Digne de confiance', 'Les humains sont réputés pour leur confiance.', 1),
(12, 'Ingénieux', 'Les humains sont ingénieux.', 1),
(13, 'Têtu comme une mule', 'Les humains sont têtu comme des mules.', 1),

(14, 'Agile', 'Les halfelins sont agiles.', 4),
(15, 'Peuple agreste', 'Les halfelins sont des peuples agrestes.', 4),
(16, 'Résilience à la magie', 'Les halfelins sont résilients à la magie.', 4);

ALTER TABLE particularite ALTER COLUMN idParticularite RESTART WITH 17;

-- Ajout des réputations
INSERT INTO reputationWiki (IDREPUTATIONWIKI, territoire, valeur, idRace) VALUES
(1, 'Nord', 'Craint et haïs', 5),
(2, 'Nilfgaard', 'Craint et haïs', 5),
(3, 'Skellige', 'Toléré', 5),
(4, 'Dol Blathana', 'Toléré', 5),
(5, 'Mahakam', 'Toléré', 5),

(6, 'Nord', 'Haïs', 3),
(7, 'Nilfgaard', 'Neutre', 3),
(8, 'Skellige', 'Neutre', 3),
(9, 'Dol Blathana', 'Neutre', 3),
(10, 'Mahakam', 'Neutre', 3),

(11, 'Nord', 'Toléré', 2),
(12, 'Nilfgaard', 'Neutre', 2),
(13, 'Skellige', 'Neutre', 2),
(14, 'Dol Blathana', 'Neutre', 2),
(15, 'Mahakam', 'Neutre', 2),

(17, 'Nilfgaard', 'Neutre', 1),
(18, 'Skellige', 'Neutre', 1),
(19, 'Dol Blathana', 'Haïs', 1),
(20, 'Mahakam', 'Tolérés', 1),

(21, 'Nord', 'Toléré', 4),
(22, 'Nilfgaard', 'Neutre', 4),
(23, 'Skellige', 'Neutre', 4),
(24, 'Dol Blathana', 'Neutre', 4),
(25, 'Mahakam', 'Neutre', 4);

ALTER TABLE particularite ALTER COLUMN idParticularite RESTART WITH 26;
