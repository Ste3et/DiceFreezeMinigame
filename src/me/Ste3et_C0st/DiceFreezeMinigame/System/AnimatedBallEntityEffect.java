package me.Ste3et_C0st.DiceFreezeMinigame.System;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.effect.EntityEffect;
import de.slikey.effectlib.util.MathUtils;
import de.slikey.effectlib.util.ParticleEffect;
import de.slikey.effectlib.util.VectorUtils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

/**
 * Creates an animated Sphere.. Thanks to the author for sharing it!
 * https://www.youtube.com/watch?feature=player_embedded&v=RUjIw_RprRw
 * @author <a href="http://forums.bukkit.org/members/qukie.90952701/">Qukie</a>
 */
public class AnimatedBallEntityEffect extends EntityEffect {

    /**
     * ParticleType of spawned particle
     */
    public ParticleEffect particle = ParticleEffect.WITCH_MAGIC;

    /**
     * Ball particles total (150)
     */
    public int particles = 150;

    /**
     * The amount of particles, displayed in one iteration (10)
     */
    public int particlesPerIteration = 10;

    /**
     * Size of this ball (1)
     */
    public float size = 1F;

    /**
     * Factors (1, 2, 1)
     */
    public float xFactor = 1F, yFactor = 2F, zFactor = 1F;

    /**
     * Offsets (0, 0.8, 0)
     */
    public float xOffset, yOffset = 0.8F, zOffset;

    /**
     * Rotation of the ball.
     */
    public double xRotation, yRotation, zRotation = 0;

    /**
     * Internal Counter
     */
    protected int step;

    public AnimatedBallEntityEffect(EffectManager effectManager, Entity entity) {
        super(effectManager, entity);
        this.type = EffectType.REPEATING;
        this.iterations = Integer.MAX_VALUE -1;
        this.period = 1;
    }

    @Override
    public void onRun() {
        Vector vector = new Vector();
        Location location = entity.getLocation();
        for (int i = 0; i < particlesPerIteration; i++) {
            step++;

            float t = (MathUtils.PI / particles) * step;
            float r = MathUtils.sin(t) * size;
            float s = 2 * MathUtils.PI * t;

            vector.setX(xFactor * r * MathUtils.cos(s) + xOffset);
            vector.setZ(zFactor * r * MathUtils.sin(s) + zOffset);
            vector.setY(yFactor * size * MathUtils.cos(t) + yOffset);

            VectorUtils.rotateVector(vector, xRotation, yRotation, zRotation);

            particle.display(location.add(vector), visibleRange, 0, 0, 0, 0, 0);
            location.subtract(vector);
        }
    }

}