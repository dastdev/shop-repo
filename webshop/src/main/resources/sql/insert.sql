--
-- artikel
--
INSERT INTO artikel (id, artikelnummer, artikelbild, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, parentID, kategorie) VALUES (100, '123123', utl_raw.cast_to_raw('media/img/art/1.png'), 'Chromobike 2014', 'Mit dem Chromobike 2014 sind sie bestens ausgerüstet ...', 'Das Chromobike 2014 ist das neueste Modell aus dem Hause X. Es besticht durch sein geringes Gewicht und seine auffällige Form ...', 199, 5, null, 'K');
INSERT INTO artikel (id, artikelnummer, artikelbild, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, parentID, kategorie) VALUES (101, '124124', utl_raw.cast_to_raw('media/img/art/2.png'), 'Chromobike 2014 PLUS Edition', 'Mit dem Chromobike 2014 sind sie bestens ausgerüstet ...', 'Das Chromobike 2014 PLUS ist das neueste und hochwertigste Modell aus dem Hause X. Es besticht durch sein geringes Gewicht und seine auffällige Form ...', 2499, 3, null, 'K');
INSERT INTO artikel (id, artikelnummer, artikelbild, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, parentID, kategorie) VALUES (102, '124774', utl_raw.cast_to_raw('media/img/art/3.png'), 'Redhax Sattel Black', 'Harter Herrensportsattel in Schwarz', 'Mit dem Redhax Sattel wird jede Tour zum Vergnügen...', 79, 25, null, 'Z');

--
-- bestellung
--
--INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert) VALUES (400,101,'01.08.2006 00:00:00','01.08.2006 00:00:00','01.08.2006 00:00:00');
--INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert) VALUES (401,101,'02.08.2006 00:00:00','01.08.2006 00:00:00','02.08.2006 00:00:00');
--INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert) VALUES (402,102,'03.08.2006 00:00:00','01.08.2006 00:00:00','03.08.2006 00:00:00');
--INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert) VALUES (403,102,'04.08.2006 00:00:00','01.08.2006 00:00:00','04.08.2006 00:00:00');
--INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert) VALUES (404,100,'05.08.2006 00:00:00','01.08.2006 00:00:00','05.08.2006 00:00:00');

--
-- position
--
--INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (500,400,300,1,'01.08.2006 00:00:00','01.08.2006 00:00:00');
--INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (501,400,301,4,'01.08.2006 00:00:00','01.08.2006 00:00:00');
--INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (502,401,302,5,'02.08.2006 00:00:00','02.08.2006 00:00:00');
--INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (503,402,303,3,'03.08.2006 00:00:00','03.08.2006 00:00:00');
--INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (504,402,302,2,'03.08.2006 00:00:00','03.08.2006 00:00:00');
--INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (505,403,301,1,'04.08.2006 00:00:00','04.08.2006 00:00:00');
--INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (506,404,300,5,'05.08.2006 00:00:00','05.08.2006 00:00:00');
--INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (507,404,301,2,'05.08.2006 00:00:00','05.08.2006 00:00:00');
--INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (508,404,302,8,'05.08.2006 00:00:00','05.08.2006 00:00:00');