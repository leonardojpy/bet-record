package leonardojpy.bet_record.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "apostas")
public class Bet {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private double odd;
    private double stake;

    @Enumerated(EnumType.STRING)
    private Result result;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

