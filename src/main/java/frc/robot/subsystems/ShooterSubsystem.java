package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private final SparkMax shooterMotorController = new SparkMax(Constants.ShooterConstants.shooterMotorControllerID, SparkLowLevel.MotorType.kBrushless);

    public void flywheelOut() {
        shooterMotorController.setVoltage(Constants.ShooterConstants.shooterVoltage);
    }

    public void shooterStop() {
        shooterMotorController.setVoltage(0);
    }
}
