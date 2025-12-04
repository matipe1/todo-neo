-- =====================================
-- Workspace, Label
-- =====================================
CREATE TABLE workspace (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE label (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL -- e.g., Work, Personal, Gym
);

-- ====================================
-- Task
-- ====================================
CREATE TABLE task (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR,

    status VARCHAR(50) NOT NULL DEFAULT 'TODO',
    priority VARCHAR(20) NOT NULL DEFAULT 'LOW',

    due_date TIMESTAMPTZ,
    reminder_date TIMESTAMPTZ,

    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,

    workspace_id INT,
    CONSTRAINT fk_workspace FOREIGN KEY (workspace_id) REFERENCES workspace(id)
);

-- ====================================
-- Task_Label (Many-to-Many)
-- ====================================
CREATE TABLE task_label (
    task_id INT NOT NULL,
    label_id INT NOT NULL,
    PRIMARY KEY (task_id, label_id),
    CONSTRAINT fk_tl_task FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE,
    CONSTRAINT fk_tl_label FOREIGN KEY (label_id) REFERENCES label(id) ON DELETE CASCADE
);