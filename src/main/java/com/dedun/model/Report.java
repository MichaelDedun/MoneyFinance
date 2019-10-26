package com.dedun.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "report_profit",
            joinColumns = {@JoinColumn(name = "report_id")},
            inverseJoinColumns = {@JoinColumn(name = "profit_id")}
    )
    private List<Profit> profits;

    @ManyToMany
    @JoinTable(
            name = "report_loss",
            joinColumns = {@JoinColumn(name = "report_id")},
            inverseJoinColumns = {@JoinColumn(name = "loss_id")}
    )
    private List<Loss> losses;

    @ManyToMany
    @JoinTable(
            name = "report_limit",
            joinColumns = {@JoinColumn(name = "report_id")},
            inverseJoinColumns = {@JoinColumn(name = "limit_id")}
    )
    private List<Limit> limits;

    public Report() {
    }

    public Report(User user,LocalDateTime dateStart, LocalDateTime dateEnd,  List<Profit> profits, List<Loss> losses, List<Limit> limits) {
        this.user = user;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.profits = profits;
        this.losses = losses;
        this.limits = limits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<Limit> getLimits() {
        return limits;
    }

    public void setLimits(List<Limit> limits) {
        this.limits = limits;
    }

    public List<Profit> getProfits() {
        return profits;
    }

    public void setProfits(List<Profit> profits) {
        this.profits = profits;
    }

    public List<Loss> getLosses() {
        return losses;
    }

    public void setLosses(List<Loss> losses) {
        this.losses = losses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return getId() == report.getId() &&
                Objects.equals(getDateStart(), report.getDateStart()) &&
                Objects.equals(getDateEnd(), report.getDateEnd()) &&
                Objects.equals(getUser(), report.getUser()) &&
                Objects.equals(getLimits(), report.getLimits()) &&
                Objects.equals(getProfits(), report.getProfits()) &&
                Objects.equals(getLosses(), report.getLosses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateStart(), getDateEnd(), getUser(), getLimits(), getProfits(), getLosses());
    }
}
