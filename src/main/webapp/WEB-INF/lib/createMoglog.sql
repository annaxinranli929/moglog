USE moglog;

CREATE TABLE posts (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100),
                       author VARCHAR(50),
                       content TEXT,
                       image_path VARCHAR(255),
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       likes INT DEFAULT 0,
                       deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE comments (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          post_id INT NOT NULL,
                          author VARCHAR(50) NOT NULL,
                          content TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (post_id) REFERENCES posts(id)
);