CREATE TABLE historical_lesson (
    historical_lesson_id SERIAL PRIMARY KEY,
    lesson_id INT NOT NULL,
    lesson_type VARCHAR(500) NOT NULL,
    genre_type VARCHAR(500),
    instrument_type VARCHAR(500),
    lesson_price INT NOT NULL,
    student_name VARCHAR(500) NOT NULL,
    student_email VARCHAR(500) NOT NULL
);