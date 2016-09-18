package org.stepic.java.week3.lesson3.task12;

/**
 * Created by Dmitry on 18.09.2016.
 */
public final class Solution {
    public static void main(String[] args) {
        Robot robot = new Robot();
        robot.Print();
        //robot.stepForward(); // your implementation here
        //robot.Print();

        moveRobot(robot, -3,4);

    }
    public static void moveRobot(Robot robot, int toX, int toY) {


        while(toX - robot.getX() != 0){
            if (toX - robot.getX() > 0){
                LookAt(robot, Direction.RIGHT);
            } else {
                LookAt(robot, Direction.LEFT);
            }
            robot.stepForward();
        }
        while(toY - robot.getY() != 0){
            if (toY - robot.getY() > 0){
                LookAt(robot, Direction.UP);
            } else {
                LookAt(robot, Direction.DOWN);
            }
            robot.stepForward();
        }


    }
    public static void LookAt(Robot robot, Direction direction){
        while(robot.getDirection() != direction){
            robot.turnRight();
        }
    }

}

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

class Robot {
    private Direction direction = Direction.UP;
    private int x , y;

    public Robot (int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Robot (){
        this(0,0);
    }
    public Direction getDirection() {
        return  this.direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void turnLeft() {
        switch (this.direction){
            case UP:    this.direction = Direction.LEFT;    break;
            case RIGHT: this.direction = Direction.UP;      break;
            case DOWN:  this.direction = Direction.RIGHT;   break;
            case LEFT:  this.direction = Direction.DOWN;    break;
        }
    }

    public void turnRight() {
        switch (this.direction){
            case UP:    this.direction = Direction.RIGHT;   break;
            case RIGHT: this.direction = Direction.DOWN;    break;
            case DOWN:  this.direction = Direction.LEFT;    break;
            case LEFT:  this.direction = Direction.UP;      break;
        }
    }

    public void stepForward() {
        switch (this.direction){
            case UP:    this.y++; break;
            case RIGHT: this.x++; break;
            case DOWN:  this.y--; break;
            case LEFT:  this.x--; break;
        }
    }

    public void Print(){
        System.out.println("Direction: "+ this.direction + " x: "+ this.x + " y: "+ this.y);
    }

    public String toString(){
        return("Direction: "+ this.direction + " x: "+ this.x + " y: "+ this.y);
    }
}