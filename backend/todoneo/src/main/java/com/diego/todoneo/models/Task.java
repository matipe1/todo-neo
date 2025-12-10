package com.diego.todoneo.models;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diego.todoneo.models.enums.TaskPriority;
import com.diego.todoneo.models.enums.TaskStatus;
import com.diego.todoneo.patterns.state.TaskState;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "task")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", nullable = true, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    @Builder.Default
    private TaskStatus status = TaskStatus.TODO;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 20)
    @Builder.Default
    private TaskPriority priority = TaskPriority.LOW;

    @Column(name = "due_date", nullable = true)
    private Instant dueDate;

    @Column(name = "reminder_date", nullable = true)
    private Instant reminderDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
    
    @Column(name = "completed_at", nullable = true)
    private Instant completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_workspace"))
    private Workspace workspace;

    @ManyToMany
    @JoinTable(
        name = "task_label",
        joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_task")),
        inverseJoinColumns = @JoinColumn(name = "label_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_label"))
    )
    @Builder.Default
    private Set<Label> labels = new HashSet<>();

    public void start(TaskState state) {
        state.start(this);
    }

    public void finish(TaskState state, Instant completionTime) {
        state.finish(this, completionTime);
    }

    public void cancel(TaskState state) {
        state.cancel(this);
    }

    public void archive(TaskState state) {
        state.archive(this);
    }

    public void back(TaskState state) {
        state.back(this);
    }

    public void reopen(TaskState state) {
        state.reopen(this);
    }

    public void unarchive(TaskState state) {
        state.unarchive(this);
    }
}