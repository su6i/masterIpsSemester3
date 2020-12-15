-- drop table data;

CREATE TABLE IF NOT EXISTS `data` (
`name` varchar(255) NULL,
`price` INT NULL,
`image` varchar(255) NULL,
`category` INT NULL,
`type` INT NULL,
`description` varchar(255) NULL
);


INSERT INTO data VALUES
('Adaptateur Smart Things - HD2018GH',39,'/assets/image/1.jpg',1,1,'Contrôle à distance'),
('Coque en silicone pour S20 FE | FE 5G',40,'/assets/image/8.jpg',1,1,'Protection contre les chocs et les rayures\,Finition douce offrant une bonne prise en main'),
('Étui en aramide pour Galaxy Z Fold2 5G',75,'/assets/image/9.jpg',1,1,'Design renforcé\,2 angles de visualisation\,Ultra fin et léger\,Toucher agréable'),
('Étui en cuir pour Galaxy Z Fold2 5G',100,'/assets/image/10.jpg',1,1,'Finition cuir\,Bonne prise en main\,Protection contre les chocs et rayures\,Doublure fine et protectrice à l''intérieur'),
('Galaxy Watch3 Bluetooth (45mm)',659,'/assets/image/11.jpeg',1,2,'Design affirmé avec lunette rotative\,Personnalisation bracelet et cadran\,Sport & bien-être\,Étanche jusqu’à 50m'),
('Galaxy Watch3 4G (45mm)',509,'/assets/image/5.jpeg',1,2,'Design affirmé avec lunette rotative\,Personnalisation bracelet et cadran\,Sport & bien-être\,Étanche jusqu’à 50m');



INSERT INTO data VALUES

('Galaxy Watch3 Bluetooth (41mm)',429,'/assets/image/6.jpg',1,2,'Design affirmé avec lunette rotative\,Personnalisation bracelet et cadran\,Sport & bien-être\,Étanche jusqu à 50m'),
('Galaxy Watch 46 mm eSIM 4G ',329,'/assets/image/7.jpeg',1,2,'Design soigné avec lunette rotative\,Connectivité cellulaire\,Fonctionnalités sport et bien-être\,Votre musique vous suit partout'),
('USB 3.0 Flash Drive DUO ',29,'/assets/image/1.jpg',1,3,'Une vitesse de transfert allant jusqu’à 130MB/s\,Se connecte aux Smartphones\, tablets et PC par micro USB\,Protection à 5 niveaux'),
('USB 3.0 Flash Drive FIT ',15,'/assets/image/2.jpg',1,3,'Une vitesse de transfert allant jusqu’à 130MB/s\,Se connecte aux netbooks et PC ultra fins\,Protection à 5 niveaux'),
('USB 3.0 Flash Drive BAR ',20,'/assets/image/3.jpg',1,3,'Une vitesse de transfert allant jusqu’à 130MB/s\,Design en métal haut de gamme\,Protection à 5 niveaux'),
('Galaxy S20 FE',659,'/assets/image/12.jpg',1,4,'Des photos lumineuses de jour et de nuit\,Grand écran  6.5 pouce ultra fluide 120Hz\,Space Zoom 30x'),
('Galaxy A71',469,'/assets/image/14.jpeg',1,4,'Ecran Infinity 6.7 pouce Super AMOLED\,Quadruple capteur photo - Mode Macro\,Capteur principal 64 MP pour des photos nettes\,Charge ultra rapide - Batterie longue durée - 4500'),
('Galaxy Z Fold2 5G',2020,'/assets/image/13.jpg',1,4,'Grand écran pliable intérieur de 7.6 pouce\,Ecran extérieur de 6.2 pouce\,Charnière à position libre pour plus de flexibilité'),
('Galaxy A31',299,'/assets/image/15.jpg',1,4,'Écran panoramique 6.4 pouce Super AMOLED\,Quadruple capteur photo avec Ultra grand angle\,Batterie longue durée - 5000 mAh + Charge rapide\,Stockage 64 Go + lecteur d''empreinte sous l''écran'),
('Galaxy Tab A7 4G',299,'/assets/image/16.jpeg',1,5,'Un grand écran de 10.4 pouce\,Autonameie longue durée\,Samsung Kids\,Caméra en mode paysage pour vos appels vidéo');





('Galaxy Tab S7 Wi-Fi',719,'/assets/image/17.jpg',1,5,'Écran ultra immersif 11 pouce\,S Pen connecté ultra précis\,La puissance d’un ordinateur\,Stockage 128/256Go + port microSD'),
('Galaxy Tab S6 Lite',349,'/assets/image/18.jpg',1,5,'Design fin et finition en aluminum\,S Pen amélioré pour une expérience plus ergonamei\,Écran panoramique 10.4 pouce\,Mémoire embarquée 64Go'),
('Galaxy Tab S6 Gris Titane 4G',679,'/assets/image/19.jpg',1,5,'Ultrafine 5.7mm\,Écran panoramique 10.5 pouce\,S Pen avec contrôle par gestes\,Lecteur d’empreinte sous l’écran');

INSERT INTO data VALUES('iPad Air',669,'/assets/image/25.jpeg',2,5,'10.9 pouce Écran Liquid Retina\,Puce A14 Bionic\,Compatible avec le Magic Keyboard et le Smart Keyboard Folio\,Compatible avec l’Apple Pencil 2 génération');
INSERT INTO data VALUES
('iPad Pro',899,'/assets/image/24.jpeg',2,5,'12.9 pouce  et 11 pouce  Écran Liquid Retina avec ProMotion\,Puce A12Z Bionic\,Compatible avec le Magic Keyboard et le Smart Keyboard Folio\,Compatible avec l’Apple Pencil (2 génération)'),
('iPad',389,'/assets/image/26.jpeg',2,5,'10.2 pouce Écran Retina\,Puce A12 Bionic\,Compatible avec le Smart Keyboard\,Compatible avec l’Apple Pencil (1re génération)'),
('iPad mini',459,'/assets/image/27.jpeg',2,5,'7\,9 pouce Écran Retina\,Puce A12 Bionic\,Compatible avec les claviers Bluetooth\,Compatible avec l’Apple Pencil 1re génération');




INSERT INTO data VALUES
('iPhone 11 Pro',1159,'/assets/image/21.png',2,4,'Triple appareil photo \, grand angle\,Jusqu’à 17 heures de lecture vidéo\,Résistant à l’eau jusqu’à 2 mètres de profondeur pendant 30 minutes maximum\,Écran Super Retina XDR 5\,8pouce \,Écran Liquid Retina HD 6.1 pouce'),
('iPhone 11',809,'/assets/image/22.png',2,4,'Double appareil photo (ultra grand‑angle\, grand‑angle)\,Quadruple capteur photo - Mode Macro\,Capteur principal 64 MP pour des photos nettes\,Charge ultra rapide Batterie longue durée  4500'),
('iPhone X',709,'/assets/image/23.png',2,4,'Appareil photo(grand‑angle)\,Jusqu’à 16 heures de lecture vidéo\,Résistant à l’eau jusqu’à 1 mètre de profondeur pendant 30 minutes maximum\,Écran Liquid Retina HD  6.1  pouce '),
('iPhone SE',489,'/assets/image/20.png',2,4,'Appareil photo(grand‑angle)\,Jusqu’à 13 heures de lecture vidéo\,Résistant à l’eau jusqu’à 1 mètre de profondeur pendant 30 minutes maximum\,Écran Retina HD 4.7 pouce');

INSERT INTO data VALUES
('Apple Watch Series 6',429,'/assets/image/36.jpeg',2,2,'Boîtier de 40 mm\,Écran Retina toujours activé\,GPS \,App ECG\,Notifications de fréquence cardiaque élevée ou faible Notifications d’arythmie\,Configuration familiale prise en charge\,Résistance à l’eau jusqu’à 50 mètres'),
('Apple Watch SE',299,'/assets/image/37.jpeg',2,2,'Boîtier de 40 ou 44 mm\,Écran Retina \,GPS + Cellular1 GPS\,Notifications de fréquence cardiaque élevée ou faible Notifications d’arythmie\,Configuration familiale prise en charge (modèles GPS + Cellular)\,Résistance à l’eau jusqu’à 50 mètres');


INSERT INTO data VALUES
('Apple Watch Series 3',219,'/assets/image/38.jpeg',2,2,'Boîtier de 38 ou 42 mm\,Écran Retina\,GPS\,Notifications de fréquence cardiaque élevée ou faible Notifications d’arythmie\,Résistance à l’eau jusqu’à 50 mètres'),
('HomePod',329,'/assets/image/35.jpeg',2,1,'Sans-fil\,Bluetooth 5.0\,Accès invité direct\,Micro interne de calibration des fréquences basses pour une correction automatique des graves\,Formation de faisceaux pour le son direct et ambiant'),
('HomePod mini',99,'/assets/image/34.jpeg',2,1,'Son riche et ample\,Assistant intelligent\,Contrôle de votre maison connectée\,Confidentialité et sécurité'),
('AirPods Pro',279,'/assets/image/33.png',2,1,'Réduction active du bruit\,Micro intérieur\,Capteur-pression\,Résistance à l’eau et à la transpiration\,Boîtier de charge sans fil\,Gravure personnalisée : initiales\, emoji\, etc.'),
('MacBook Air',1199,'/assets/image/31.jpeg',2,3,'Écran Retina 13.3 pouces\,Jusqu’au processeur Intel Core i7 quadricœur\,Jusqu’à 16 Go de mémoire\,Jusqu’à 2 To de stockage\,Jusqu’à 11 h d’autonameie\,Touch ID\,Magic Keyboard rétroéclairé'),
('MacBook Pro 13”',1499,'/assets/image/32.jpeg',2,3,'Écran Retina 13.3 pouces\,Jusqu’au processeur Intel Core i7 quadricœur\,Jusqu’à 32 Go de mémoire\,Jusqu’à 4 To de stockage\,Jusqu’à 10 h d’autonameie\,Touch Bar et Touch ID\,Magic Keyboard rétroéclairé'),
('MacBook Pro 16” ',2699,'/assets/image/30.jpeg',2,3,'Écran Retina 16pouces\,Jusqu’au processeur Intel Core i9 8 cœurs\,Jusqu’à 64 Go de mémoire\,Jusqu’à 8 To de stockage\,Jusqu’à 11 h d’autonameie\,Touch Bar et Touch ID\,Magic Keyboard rétroéclairé'),
('MatePad Pro',550,'/assets/image/39.png',3,5,'Écran Huawei FullView de 10.8 pouces\,Processeur Kirin 990\,Quadruple haut-parleur\,Compatible Huawei Share\, Mode PC\, Clavier'),
('MediaPad M5 lite',230,'/assets/image/40.png',3,5,'Ecran 10.1 pouce 1080p\,Quatre haut-parleurs stéréo\,Mode confort des yeux'),
('MediaPad M6 10.8”',350,'/assets/image/41.jpg',3,5,'[\"Processeur Kirin 980\"\,\"Ecran 2K\"\,\"Quadruple haut-parleur Harman Kardon\"]'),
('MediaPad M5 lite 8',149,'/assets/image/41.png',3,5,'Ecran 8.0” 283PPI\,Quadruple haut-parleur Harman Kardon\,Batterie de 5100 mAh'),
('P40 Pro+',1200,'/assets/image/42.png',3,4,'Quintuple capteur Ultra Vision conçu avec Leica\,Zoom optique 10x\, zoom max 100x\,Ecran incurvé Overflow\, IP68'),
('P30 Pro New Edition',660,'/assets/image/44.png',3,4,'8 Go de RAM et 256 Go de stockage\,Quadruple caméra SuperSensing conçue avec Leica\,Zoom jusqu''à 50x'),
('P smart S',230,'/assets/image/43.png',3,4,'Triple caméra dotée d''IA et capteur principal de 48 MP1\,Stockage de 128 Go\,Ecran OLED de 6.3 pouce'),
('P30 Pro',600,'/assets/image/45.png',3,4,'Écran OLED de 6.47 pouces\, IP68\,Quadruple Caméra Leica\, Zoom hybride 10x\, Capteur SuperSpectrum\,Technologie Huawei SuperCharge 40 W'),
('FreeBuds 3i',90,'/assets/image/51.jpg',3,1,'Payez en 3x ou 4x sans frais\, disponible uniquement pour les commandes supérieures à 149\,Réduction de bruit active\,3 microphones pour une qualité d''appel exceptionnelle\,Design intra-auriculaire avec 4 paires d''ambouts de différentes taille'),
('Sound X',270,'/assets/image/50.png',3,1,'Double Woofers Devialet\,Architecture acoustique ''Push-Push'' de D\,Technologie Huawei Share'),
('FreeBuds 3',130,'/assets/image/49.png',3,1,'Réduction active du bruit\,Processeur Kirin A1\, temps de latence ultr\,Charge sans-fil\, autonameie longue durée'),
('MateBook D 14 2020',600,'/assets/image/48.png',3,3,'Windows 10 Home\,Ecran FullView de 14 pouces\,Continuité multi-écrans'),
('MateBook 14 2020',1050,'/assets/image/47.png',3,3,'Windows 10 Home\,10ème Génération de processeurs Intel®Core™\,Ecran Huawei FullView de 14 pouces'),
('MateBook X Pro 2020',1300,'/assets/image/46.png',3,3,'Windows 10 Home\,Écran FullView 3K\,Huawei Share\,10 ème Génération de processeurs Intel®\,Jusqu’à 11 h d’autonameie');
