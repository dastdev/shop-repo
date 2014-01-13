--
-- artikel
--
INSERT INTO artikel (id, artikelnummer, artikelbild, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, parentID, kategorie) VALUES (1, '123123', utl_raw.cast_to_raw('media/img/art/1.png'), 'Chromobike 2014', 'Mit dem Chromobike 2014 sind sie bestens ausgerüstet ...', 'Das Chromobike 2014 ist das neueste Modell aus dem Hause X. Es besticht durch sein geringes Gewicht und seine auffällige Form ...', 1999.99, 5, null, 'K');
INSERT INTO artikel (id, artikelnummer, artikelbild, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, parentID, kategorie) VALUES (2, '124124', utl_raw.cast_to_raw('media/img/art/2.png'), 'Chromobike 2014 PLUS Edition', 'Mit dem Chromobike 2014 sind sie bestens ausgerüstet ...', 'Das Chromobike 2014 PLUS ist das neueste und hochwertigste Modell aus dem Hause X. Es besticht durch sein geringes Gewicht und seine auffällige Form ...', 2499.99, 3, null, 'K');
INSERT INTO artikel (id, artikelnummer, artikelbild, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, parentID, kategorie) VALUES (3, '124774', utl_raw.cast_to_raw('media/img/art/3.png'), 'Redhax Sattel Black', 'Harter Herrensportsattel in Schwarz', 'Mit dem Redhax Sattel wird jede Tour zum Vergnügen...', 79.99, 25, null, 'Z');
