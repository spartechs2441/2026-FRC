package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class IndexerSubsystem extends SubsystemBase {
    private final SparkMax loader = new SparkMax(Constants.IndexerConstants.loaderId, SparkLowLevel.MotorType.kBrushless);

    public IndexerSubsystem() {
        Robot.invertMotor(loader);
    }

    public void loaderIn() {
        loader.setVoltage(Constants.IndexerConstants.loaderVoltage);
    }

    public void loaderOut() {
        loader.setVoltage(-Constants.IndexerConstants.loaderVoltage);
    }

    public void loaderStop() {
        loader.setVoltage(0);
    }
}

