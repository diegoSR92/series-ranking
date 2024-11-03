-- Insertar datos en la tabla Serie
INSERT INTO Serie (nombre, plataforma, caratula, sinopsis) VALUES
('Breaking Bad', 'Netflix', 'https://static1.srcdn.com/wordpress/wp-content/uploads/2023/02/breaking-bad-tv-poster.jpg?q=50&fit=contain&h=392&dpr=1.5', 'Narra la historia de un profesor de química con problemas económicos a quien le diagnostican un cáncer de pulmón inoperable.'),
('Band of Brothers', 'HBO', 'https://pics.filmaffinity.com/band_of_brothers-541225951-large.jpg', 'La acción se centra en lo experimentado por la Compañía Easy del 506.º Regimiento de Infantería de Paracaidistas del Ejército de los Estados Unidos.'),
('Chernobyl', 'HBO', 'https://pics.filmaffinity.com/chernobyl-183665235-large.jpg', 'La serie gira en torno al desastre nuclear de Chernóbil de abril de 1986 y los esfuerzos de limpieza sin precedentes que siguieron'),
('The Boys', 'Prime Video', 'https://pics.filmaffinity.com/the_boys-969809291-large.jpg', 'Sigue al equipo homónimo de justicieros en su lucha contra diversos individuos con superpoderes que abusan de sus habilidades.');

-- Insertar datos en la tabla Valoracion
INSERT INTO Valoracion (serie_id, autor, valoracion) VALUES
(2, 'Marta', 9.5),
(2, 'Pedro', 9.4),
(2, 'Laura', 9.3),
(1, 'David', 9.9),
(1, 'Pedro', 9.8),
(1, 'Javier', 9.7),
(3, 'David', 9.4),
(3, 'Marta', 9.3),
(3, 'Javier', 9.2),
(4, 'David', 9.9),
(4, 'Laura', 9.1),
(4, 'Javier', 8.5),
;