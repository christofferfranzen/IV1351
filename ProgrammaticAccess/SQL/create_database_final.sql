CREATE TABLE address (
    address_id SERIAL PRIMARY KEY,
    zip_code VARCHAR(500) NOT NULL,
    city VARCHAR(500) NOT NULL,
    street_name VARCHAR(500) NOT NULL
);

CREATE TABLE contact_detail (
    contact_detail_id SERIAL PRIMARY KEY,
    value VARCHAR(500) NOT NULL
);

CREATE TABLE difficulty (
    difficulty_id SERIAL PRIMARY KEY,
    value VARCHAR(500) NOT NULL CHECK (value IN ('Beginner', 'Intermediate', 'Advance'))
);

CREATE TABLE genre_type (
    genre_type_id SERIAL PRIMARY KEY,
    value VARCHAR(500) NOT NULL
);

CREATE TABLE instrument (
    instrument_id SERIAL PRIMARY KEY,
    serial_number VARCHAR(500) NOT NULL
);

CREATE TABLE instrument_type (
    instrument_type_id SERIAL PRIMARY KEY,
    value VARCHAR(500) NOT NULL
);

CREATE TABLE person (
    person_id SERIAL PRIMARY KEY,
    person_number VARCHAR(500) NOT NULL,
    first_name VARCHAR(500) NOT NULL,
    last_name VARCHAR(500) NOT NULL
);

CREATE TABLE person_contact_detail (
    person_id INT NOT NULL,
    contact_detail_id SERIAL NOT NULL,
    PRIMARY KEY (person_id, contact_detail_id),
    FOREIGN KEY (contact_detail_id) REFERENCES contact_detail (contact_detail_id),
    FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE pricing_scheme (
    pricing_scheme_id SERIAL PRIMARY KEY,
    discount_percent INT NOT NULL,
    student_price INT NOT NULL,
    instructor_price INT NOT NULL
);

CREATE TABLE student (
    student_id SERIAL PRIMARY KEY,
    address_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES person (person_id),
    FOREIGN KEY (address_id) REFERENCES address (address_id)
);

CREATE TABLE contact_person (
    contact_person_id SERIAL PRIMARY KEY
);

CREATE TABLE contact_person_relation (
    contact_person_id SERIAL NOT NULL,
    student_id SERIAL NOT NULL,
    PRIMARY KEY (contact_person_id, student_id),
    FOREIGN KEY (contact_person_id) REFERENCES contact_person (contact_person_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id)
);

CREATE TABLE instructor (
    instructor_id SERIAL PRIMARY KEY,
    address_id INT NOT NULL,
    FOREIGN KEY (instructor_id) REFERENCES person (person_id),
    FOREIGN KEY (address_id) REFERENCES address (address_id)
);

CREATE TABLE instrument_rent (
    instrument_rent_id SERIAL PRIMARY KEY,
    instrument_type_id INT NOT NULL,
    brand VARCHAR(500) NOT NULL,
    price INT NOT NULL,
    FOREIGN KEY (instrument_type_id) REFERENCES instrument_type (instrument_type_id)
);

CREATE TABLE lesson_type (
    lesson_type_id SERIAL PRIMARY KEY,
    value VARCHAR(500) NOT NULL CHECK (value IN ('Group', 'Private', 'Ensemble')),
    pricing_scheme_id INT NOT NULL,
    instrument_type_id INT,
    genre_type_id INT,
    difficulty_id INT,
    FOREIGN KEY (pricing_scheme_id) REFERENCES pricing_scheme (pricing_scheme_id),
    FOREIGN KEY (genre_type_id) REFERENCES genre_type (genre_type_id),
    FOREIGN KEY (instrument_type_id) REFERENCES instrument_type (instrument_type_id),
    FOREIGN KEY (difficulty_id) REFERENCES difficulty (difficulty_id)
);

CREATE TABLE rental (
    student_id SERIAL NOT NULL,
    instrument_id SERIAL NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    PRIMARY KEY (student_id, instrument_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (instrument_id) REFERENCES instrument (instrument_id)
);

CREATE TABLE sibling (
    sibling_id SERIAL PRIMARY KEY
);

CREATE TABLE sibling_relation (
    sibling_id SERIAL NOT NULL,
    student_id SERIAL NOT NULL,
    PRIMARY KEY (sibling_id, student_id),
    FOREIGN KEY (sibling_id) REFERENCES sibling (sibling_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id)
);

CREATE TABLE can_teach_instrument (
    instructor_id SERIAL NOT NULL,
    instrument_type_id SERIAL NOT NULL,
    PRIMARY KEY (instructor_id, instrument_type_id),
    FOREIGN KEY (instrument_type_id) REFERENCES instrument_type (instrument_type_id),
    FOREIGN KEY (instructor_id) REFERENCES instructor (instructor_id)
);

CREATE TABLE instrument_information_relation (
    instrument_id SERIAL NOT NULL,
    instrument_rent_id SERIAL NOT NULL,
    PRIMARY KEY (instrument_id, instrument_rent_id),
    FOREIGN KEY (instrument_id) REFERENCES instrument (instrument_id),
    FOREIGN KEY (instrument_rent_id) REFERENCES instrument_rent (instrument_rent_id)
);

CREATE TABLE lesson (
    lesson_id SERIAL PRIMARY KEY,
    start_time TIMESTAMP(6) NOT NULL,
    end_time TIMESTAMP(6) NOT NULL,
    minimum_of_students INT NOT NULL,
    max_of_students INT NOT NULL,
    instructor_id SERIAL NOT NULL,
    lesson_type_id SERIAL NOT NULL,
    FOREIGN KEY (instructor_id) REFERENCES instructor (instructor_id),
    FOREIGN KEY (lesson_type_id) REFERENCES lesson_type (lesson_type_id)
);

-- Function to check for overlapping lessons for the same instructor
CREATE OR REPLACE FUNCTION check_instructor_schedule()
RETURNS TRIGGER AS $$
BEGIN
    IF (
        EXISTS (
            SELECT 1
            FROM lesson l1, lesson l2
            WHERE
                l1.instructor_id = NEW.instructor_id
                AND l1.lesson_id <> NEW.lesson_id
                AND (
                    (NEW.start_time, NEW.end_time) OVERLAPS (l2.start_time, l2.end_time)
                )
        )
    ) THEN
        RAISE EXCEPTION 'Instructor cannot be scheduled for two lessons at the same time';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Attach the trigger to the lesson table
CREATE TRIGGER check_instructor_schedule_trigger
BEFORE INSERT OR UPDATE ON lesson
FOR EACH ROW EXECUTE FUNCTION check_instructor_schedule();

CREATE TABLE lesson_booking (
    student_id SERIAL NOT NULL,
    lesson_id SERIAL NOT NULL,
    PRIMARY KEY (student_id, lesson_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (lesson_id) REFERENCES lesson (lesson_id)
);
