--
-- artikel
--
INSERT INTO artikel (id, artikelnummer, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, kategorie) VALUES (100, '123123', 'Chromobike 2014', 'Mit dem Chromobike 2014 sind sie bestens ausgerüstet ...', 'Das Chromobike 2014 ist das neueste Modell aus dem Hause X. Es besticht durch sein geringes Gewicht und seine auffällige Form ...', 199, 5, 'K');
INSERT INTO artikel (id, artikelnummer, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, kategorie) VALUES (101, '124124', 'Chromobike 2014 PLUS Edition', 'Mit dem Chromobike 2014 sind sie bestens ausgerüstet ...', 'Das Chromobike 2014 PLUS ist das neueste und hochwertigste Modell aus dem Hause X. Es besticht durch sein geringes Gewicht und seine auffällige Form ...', 2499, 3, 'K');
INSERT INTO artikel (id, artikelnummer, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, kategorie) VALUES (102, '124774', 'Redhax Sattel Black', 'Harter Herrensportsattel in Schwarz', 'Mit dem Redhax Sattel wird jede Tour zum Vergnügen...', 79, 25, 'Z');

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