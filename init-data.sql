-- 院校数据
INSERT INTO university (name_cn, name_en, country, rank, location, website) VALUES
('University of Oxford', 'Oxford', 'UK', 1, 'Oxford', 'www.ox.ac.uk'),
('University of Cambridge', 'Cambridge', 'UK', 2, 'Cambridge', 'www.cam.ac.uk'),
('Imperial College London', 'Imperial', 'UK', 3, 'London', 'www.imperial.ac.uk'),
('UCL', 'UCL', 'UK', 4, 'London', 'www.ucl.ac.uk'),
('University of Edinburgh', 'Edinburgh', 'UK', 5, 'Edinburgh', 'www.ed.ac.uk'),
('University of Manchester', 'Manchester', 'UK', 6, 'Manchester', 'www.manchester.ac.uk'),
('King''s College London', 'KCL', 'UK', 7, 'London', 'www.kcl.ac.uk'),
('The University of Hong Kong', 'HKU', 'HK', 1, 'Hong Kong', 'www.hku.hk'),
('CUHK', 'CUHK', 'HK', 2, 'Hong Kong', 'www.cuhk.edu.hk'),
('HKUST', 'HKUST', 'HK', 3, 'Hong Kong', 'www.hkust.edu.hk'),
('CityU', 'CityU', 'HK', 4, 'Hong Kong', 'www.cityu.edu.hk'),
('ANU', 'ANU', 'AU', 1, 'Canberra', 'www.anu.edu.au'),
('University of Melbourne', 'Melbourne', 'AU', 2, 'Melbourne', 'www.unimelb.edu.au'),
('University of Sydney', 'Sydney', 'AU', 3, 'Sydney', 'www.sydney.edu.au'),
('UNSW', 'UNSW', 'AU', 4, 'Sydney', 'www.unsw.edu.au'),
('UQ', 'UQ', 'AU', 5, 'Brisbane', 'www.uq.edu.au'),
('NUS', 'NUS', 'SG', 1, 'Singapore', 'www.nus.edu.sg'),
('NTU', 'NTU', 'SG', 2, 'Singapore', 'www.ntu.edu.sg');

-- 专业数据
INSERT INTO major (university_id, name_cn, name_en, degree_type, duration, tuition, language_req, academic_req, deadline) VALUES
(1, 'MSc Computer Science', 'Computer Science', 'Master', '1 year', 35000, 'IELTS 7.0', 'GPA 3.5', '2024-01-15'),
(1, 'MSc Finance', 'Finance', 'Master', '1 year', 38000, 'IELTS 7.0', 'GPA 3.5', '2024-01-15'),
(1, 'MSc Artificial Intelligence', 'AI', 'Master', '1 year', 36000, 'IELTS 7.0', 'GPA 3.5', '2024-01-15'),
(2, 'MPhil Computer Science', 'Computer Science', 'Master', '2 years', 25000, 'IELTS 7.0', 'GPA 3.5', '2024-01-31'),
(3, 'MSc Finance', 'Finance', 'Master', '1 year', 35000, 'IELTS 7.0', 'GPA 3.3', '2024-03-01'),
(3, 'MSc Business Analytics', 'Business Analytics', 'Master', '1 year', 34000, 'IELTS 7.0', 'GPA 3.3', '2024-03-01'),
(8, 'Master of Finance', 'Finance', 'Master', '1 year', 24000, 'IELTS 6.5', 'GPA 3.0', '2024-02-28'),
(8, 'Master of Business Analytics', 'Business Analytics', 'Master', '1 year', 22000, 'IELTS 6.5', 'GPA 3.0', '2024-02-28'),
(12, 'Master of Computing', 'Computing', 'Master', '2 years', 50000, 'IELTS 6.5', 'GPA 3.0', '2024-03-15'),
(12, 'Master of Data Science', 'Data Science', 'Master', '2 years', 48000, 'IELTS 6.5', 'GPA 3.0', '2024-03-15'),
(17, 'MSc Computer Science', 'Computer Science', 'Master', '1.5 years', 45000, 'IELTS 6.5', 'GPA 3.0', '2024-02-01'),
(17, 'MSc Finance', 'Finance', 'Master', '1.5 years', 42000, 'IELTS 6.5', 'GPA 3.0', '2024-02-01');