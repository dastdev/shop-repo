-- Allgemeines --
-- ID-Vergabe		Klasse			ID
-- 					Kunde 			100-200
--					Adresse			200-300
--					Artikel			300-400
--					Bestellung		400-500
--					Position		500-600

--
-- kunde
--
INSERT INTO kunde (id, name, vorname, typ, email, passwort, geburtstag, erzeugt, aktualisiert) VALUES (100,'Admin','Admin','F', 'admin@hs-karlsruhe.de', 'Admin','01.01.2001', '01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, name, vorname, typ, email, passwort, geburtstag, erzeugt, aktualisiert) VALUES (101,'Alpha','Adrian','P', 'adrian@hs-karlsruhe.de', 'Alpha','01.01.2001', '01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, name, vorname, typ, email, passwort, geburtstag, erzeugt, aktualisiert) VALUES (102,'Beta','Bea','P', 'bea@hs-karlsruhe.de', 'Beta','02.02.2001', '01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, name, vorname, typ, email, passwort, geburtstag, erzeugt, aktualisiert) VALUES (103,'Gamma','Guru','F', 'guru@hs-karlsruhe.de', 'Gamma','03.03.2001', '01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, name, vorname, typ, email, passwort, geburtstag, erzeugt, aktualisiert) VALUES (104,'Phi','Pettersson','F', 'pettersson@hs-karlsruhe.de', 'Phi','04.04.2001', '01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, name, vorname, typ, email, passwort, geburtstag, erzeugt, aktualisiert) VALUES (105,'Phi','Paula','F', 'paula@hs-karlsruhe.de', 'Phi','05.05.2001', '01.08.2006 00:00:00','01.08.2006 00:00:00');

--
-- adresse
--
INSERT INTO adresse (id, strasse, hausnummer, plz, stadt, land, kunde_fk, erzeugt, aktualisiert) VALUES (200,'Moltkestraße','13','76133','Karlsruhe',1,100,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO adresse (id, strasse, hausnummer, plz, stadt, land, kunde_fk, erzeugt, aktualisiert) VALUES (201,'Moltkestraße','13','76133','Karlsruhe',1,101,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO adresse (id, strasse, hausnummer, plz, stadt, land, kunde_fk, erzeugt, aktualisiert) VALUES (202,'Moltkestraße','13','76133','Karlsruhe',1,102,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO adresse (id, strasse, hausnummer, plz, stadt, land, kunde_fk, erzeugt, aktualisiert) VALUES (203,'Moltkestraße','13','76133','Karlsruhe',1,103,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO adresse (id, strasse, hausnummer, plz, stadt, land, kunde_fk, erzeugt, aktualisiert) VALUES (204,'Moltkestraße','13','76133','Karlsruhe',1,104,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO adresse (id, strasse, hausnummer, plz, stadt, land, kunde_fk, erzeugt, aktualisiert) VALUES (205,'Moltkestraße','13','76133','Karlsruhe',1,105,'01.08.2006 00:00:00','01.08.2006 00:00:00');

--
-- artikel
--
INSERT INTO artikel (id, artikelnummer, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, kategorie, erzeugt, aktualisiert) VALUES (300, '123123', 'Chromobike 2014', 'Mit dem Chromobike 2014 sind sie bestens ausgerüstet ...', 'Das Chromobike 2014 ist das neueste Modell aus dem Hause X. Es besticht durch sein geringes Gewicht und seine auffällige Form ...', 199, 5, 'K','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO artikel (id, artikelnummer, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, kategorie, erzeugt, aktualisiert) VALUES (301, '124124', 'Chromobike 2014 PLUS Edition', 'Mit dem Chromobike 2014 sind sie bestens ausgerüstet ...', 'Das Chromobike 2014 PLUS ist das neueste und hochwertigste Modell aus dem Hause X. Es besticht durch sein geringes Gewicht und seine auffällige Form ...', 2499, 3, 'K','02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO artikel (id, artikelnummer, bezeichnung, kurzBeschreibung, beschreibung, preis, lagerbestand, kategorie, erzeugt, aktualisiert) VALUES (302, '124774', 'Redhax Sattel Black', 'Harter Herrensportsattel in Schwarz', 'Mit dem Redhax Sattel wird jede Tour zum Vergnügen...', 79, 25, 'Z','03.08.2006 00:00:00','03.08.2006 00:00:00');


--
-- bestellung
--
INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert, idx) VALUES (400,100,'01.08.2006 00:00:00','01.08.2006 00:00:00','01.08.2006 00:00:00',0);
INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert, idx) VALUES (401,101,'02.08.2006 00:00:00','01.08.2006 00:00:00','02.08.2006 00:00:00',1);
INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert, idx) VALUES (402,102,'03.08.2006 00:00:00','01.08.2006 00:00:00','03.08.2006 00:00:00',0);
INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert, idx) VALUES (403,102,'04.08.2006 00:00:00','01.08.2006 00:00:00','04.08.2006 00:00:00',1);
INSERT INTO bestellung (id, kunde_fk, bestelldatum, erzeugt, aktualisiert, idx) VALUES (404,100,'05.08.2006 00:00:00','01.08.2006 00:00:00','05.08.2006 00:00:00',0);


--
-- position
--
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (500,400,300,1,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (501,400,301,4,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (502,401,302,5,'02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (503,402,302,3,'03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (504,402,302,2,'03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (505,403,301,1,'04.08.2006 00:00:00','04.08.2006 00:00:00');
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (506,404,300,5,'05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (507,404,301,2,'05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (508,404,302,8,'05.08.2006 00:00:00','05.08.2006 00:00:00');
