import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Robot {
    public Integer location;

    public void move(Direction direction) {
        if (direction == Direction.LEFT) {
            this.location--;
        } else {
            this.location++;
        }
    }


    public boolean lookThereIsAParachute(List<Integer> parachuteLocation) {
        return parachuteLocation.contains(this.location);
    }

    public Robot() {
        this.location = ThreadLocalRandom.current().nextInt(-10000, 10001);
    }
}

class RobotTask implements Runnable {
    private final Robot currentRobot;
    private final Robot otherRobot;
    private Direction direction;
    private int speed;

    public RobotTask(Robot currentRobot, Robot otherRobot, Direction direction, int initialSpeed) {
        this.currentRobot = currentRobot;
        this.otherRobot = otherRobot;
        this.direction = direction;
        this.speed = initialSpeed;
    }

    @Override
    public void run() {
        while (true) {
            int stepsTaken = 0;

            while (stepsTaken < speed && !Objects.equals(currentRobot.location, otherRobot.location)) {
                currentRobot.move(direction);
                stepsTaken++;
            }


            if (Objects.equals(currentRobot.location, otherRobot.location)) {
                System.out.println("Meeting point: " + currentRobot.location);
                break;
            }

            direction = (direction == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT;
            speed++;
        }
    }
}

