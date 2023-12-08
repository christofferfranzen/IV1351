-- Exempeldata för Contact_Detail Tabell
INSERT INTO contact_detail (contact_detail_id, value) VALUES
(10, 'elijah.martinez@hotmail.com'),
(11, 'grace.hernandez@gmail.com'),
(12, 'alexander.garcia@yahoo.com'),
(13, 'ludwig.martinez@example.com'),
(14, 'harry.hernandez@example.com'),
(15, 'christoffer.garcia@example.com'),

(20, 'thomas.rodriguez@example.com'),
(21, 'clara.rodriguez@example.com'),
(22, 'peter.rodriguez@example.com'),
(23, 'karl.rodriguez@example.com'),


(30, 'elijah.martinez.contact@example.com'),
(31, 'grace.hernandez.contact@example.com'),
(32, 'stefan.garcia.contact@example.com'),
(33, 'bo.hernandez.contact@example.com'),
(34, 'mikael.garcia.contact@example.com');

-- Exempeldata för Person Tabell
INSERT INTO person (person_id, person_number, first_name, last_name) VALUES

--Students--
(10, '900302', 'Elijah', 'Martinez'),
(11, '910614', 'Grace', 'Hernandez'),
(12, '920725', 'Alexander', 'Garcia'),
(13, '000302', 'Ludwig', 'Martinez'),
(14, '010614', 'Harry', 'Hernandez'),
(15, '020725', 'Christoffer', 'Garcia'),


--Instructors--
(20, '831108', 'Thomas', 'Rodriguez'),
(21, '730508', 'Clara', 'Rodriguez'),
(22, '630114', 'Peter', 'Rodriguez'),
(23, '530314', 'Karl', 'Rodriguez'),


--Contact_person--
(30, '801022', 'Elijah', 'Martinez'),
(31, '800904', 'Grace', 'Hernandez'),
(32, '80725', 'Stefan', 'Garcia'),
(33, '800904', 'Bo', 'Hernandez'),
(34, '80725', 'Mikael', 'Garcia');

-- Exempeldata för Person_Contact_Detail Tabell (Many-to-Many relation mellan Person och Contact_Detail)
INSERT INTO person_contact_detail (person_id, contact_detail_id) VALUES

--Student--
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),

--Instructor--
(20, 20),
(21, 21),
(22, 22),
(23, 23),

--Contact_person--
(30, 30),
(31, 31),
(32, 32),
(33, 33),
(34, 34);


-- Exempeldata för Adress Tabell
INSERT INTO address (address_id, zip_code, city, street_name) VALUES
(1, '12345', 'Stockholm', 'Main Street'),
(2, '54321', 'Stockholm', 'Broadway'),
(3, '67890', 'Stockholm', 'Oak Avenue'),
(4, '98765', 'Stockholm', 'Maple Street'),
(5, '54321', 'Stockholm', 'Sunset Boulevard'),
(6, '67890', 'Stockholm', 'Park Avenue'),
(7, '98765', 'Stockholm', 'Riverside Drive'),
(8, '23456', 'Stockholm', 'Ocean View Street'),
(9, '87654', 'Stockholm', 'Mountain View Road'),
(10, '21098', 'Stockholm', 'Green Valley Lane'),
(11, '34567', 'Stockholm', 'Lakefront Terrace'),
(12, '45678', 'Stockholm', 'Hillside Avenue'),
(13, '56789', 'Stockholm', 'Meadow Lane');

-- Exempeldata för Student Tabell
INSERT INTO student (student_id, address_id) VALUES
(10, 1),
(11, 1),
(12, 2),
(13, 3),
(14, 4),
(15, 5);


-- Exempeldata för Contact_Person Tabell
INSERT INTO contact_person (contact_person_id) VALUES
(30),
(31),
(32),
(33),
(34);


-- Exempeldata för Contact_Person_Relation Tabell (Many-to-Many relation mellan Contact_Person och Student)
INSERT INTO contact_person_relation (contact_person_id, student_id) VALUES
(30, 10),
(30, 11),
(31, 12),
(32, 13),
(33, 14),
(34, 15);



-- Exempeldata för Sibling Tabell
INSERT INTO sibling (sibling_id) VALUES
(10), 
(11);

-- Exempeldata för Sibling_Relation Tabell (Many-to-Many relation mellan Sibling och Student)
INSERT INTO sibling_relation (sibling_id, student_id) VALUES
(10, 11),
(11, 10);

-- Exempeldata för Instructor Tabell
INSERT INTO instructor (instructor_id, address_id) VALUES
(20, 6),
(21, 7),
(22, 8),
(23, 9);

-- Exempeldata för Instrument_Type Tabell
INSERT INTO instrument_type (instrument_type_id, value) VALUES
(1, 'Guitar'),
(2, 'Piano'),
(3, 'Violin'),
(4, 'Drums');


-- Exempeldata för Instrument Tabell
INSERT INTO instrument (instrument_id, instrument_type_id, serial_number, brand, quantity, rental_price) VALUES
(301, 1, 'ABC123', 'Fender', 5, 100),
(302, 2, 'XYZ789', 'Yamaha', 3, 200),
(303, 1, 'XYZ789', 'Yamaha', 9, 400),
(304, 3, 'DEF456', 'Gibson', 7, 500);

-- Exempeldata för Rental Tabell
INSERT INTO rental (student_id, instrument_id, start_date, end_date) VALUES
(10, 301, '2023-11-15', '2023-11-30'),
(11, 302, '2023-11-10', '2023-11-25'),
(12, 303, '2023-11-20', '2023-12-05');


-- Exempeldata för Can_Teach_Instrument Tabell
INSERT INTO can_teach_instrument (instructor_id, instrument_type_id) VALUES
(20, 1),
(20, 2),
(21, 2),
(22, 3),
(23, 4);


------------------------------------------------------


-- Exempeldata för Difficulty Tabell
INSERT INTO difficulty (difficulty_id, value) VALUES
(1, 'Beginner'),
(2, 'Intermediate'),
(3, 'Advance');

-- Exempeldata för Genre_Type Tabell
INSERT INTO genre_type (genre_type_id, value) VALUES
(1, 'Rock'),
(2, 'Jazz'),
(3, 'Pop'),
(4, 'Classical');


-- Exempeldata för Pricing_Scheme Tabell
INSERT INTO pricing_scheme (pricing_scheme_id, discount_percent, student_price, instructor_price) VALUES
(1, 10.00, '50.00', '100.00'),
(2, 15.00, '45.00', '95.00'),
(3, 20.00, '40.00', '90.00'),
(4, 12.50, '55.00', '80.00'),
(5, 18.75, '48.75', '85.50');


-- Exempeldata för Lesson Tabell
INSERT INTO lesson (lesson_id, start_time, end_time, minimum_of_students, max_of_students, instructor_id) VALUES
(401, '2023-11-17 10:00:00', '2023-11-17 12:00:00', 1, 1, 20),
(402, '2023-11-18 15:30:00', '2023-11-18 17:30:00', 2, 6, 21),
(403, '2023-11-19 09:00:00', '2023-11-19 11:00:00', 4, 10, 22);


-- Exempeldata för Lesson_Type Tabell
INSERT INTO lesson_type (lesson_id, value, pricing_scheme_id, instrument_type_id, genre_type_id, difficulty_id) VALUES
(401, 'Private', 	1, 		1, 		null,		1),
(402, 'Group', 		2,		2, 		null, 		2),
(403, 'Ensamble', 	3,		null, 	3, 			null);


-- Exempeldata för Lesson_Booking Tabell
INSERT INTO lesson_booking (student_id, lesson_id) VALUES

--Private--
(10, 401),

--Group--
(11, 402),
(12, 402),
(13, 402),

--Ensamble--
(14, 403),
(15, 403);



