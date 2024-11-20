import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Robot robot1 = new Robot();
        Robot robot2 = new Robot();
        Line line = new Line(List.of(robot1.location, robot2.location));
        Thread robot1Thread = new Thread(new RobotTask(robot1, robot2, Direction.RIGHT, 1));
        Thread robot2Thread = new Thread(new RobotTask(robot2, robot1, Direction.LEFT, 2));
        robot1Thread.start();
        robot2Thread.start();
        robot2Thread.join();
        robot1Thread.join();
    }
}

