-- Insert data into the historical_lesson table with relevant information
INSERT INTO historical_lesson (lesson_id, lesson_type, genre_type, instrument_type, lesson_price, student_name, student_email)
SELECT
    -- Map lesson_id from the source table
    l.lesson_id,
    -- Map lesson_type from the source table
    lt.value AS lesson_type,
    -- Map genre_type (if available) from the source table
    gt.value AS genre_type,
    -- Map instrument_type (if available) from the source table
    it.value AS instrument_type,
    -- Copy lesson_price from the pricing scheme in the source table
    ls.student_price AS lesson_price,
    -- Concatenate first_name and last_name to get student_name
    p.first_name || ' ' || p.last_name AS student_name,
    -- Map student email from the contact details in the source table
    cd.value AS student_email
FROM historicaldb.public.lesson l
JOIN historicaldb.public.lesson_type lt ON l.lesson_type_id = lt.lesson_type_id
LEFT JOIN historicaldb.public.genre_type gt ON lt.genre_type_id = gt.genre_type_id
LEFT JOIN historicaldb.public.instrument_type it ON lt.instrument_type_id = it.instrument_type_id
JOIN historicaldb.public.pricing_scheme ls ON lt.pricing_scheme_id = ls.pricing_scheme_id
JOIN historicaldb.public.lesson_booking lb ON l.lesson_id = lb.lesson_id
JOIN historicaldb.public.student s ON lb.student_id = s.student_id
JOIN historicaldb.public.person p ON s.student_id = p.person_id
JOIN historicaldb.public.person_contact_detail pcd ON p.person_id = pcd.person_id
JOIN historicaldb.public.contact_detail cd ON pcd.contact_detail_id = cd.contact_detail_id;