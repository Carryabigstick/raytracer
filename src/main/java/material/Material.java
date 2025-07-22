package material;

public abstract class Material
{
    public boolean scatter(util.ray r_in, geometry.HitRecord rec,
                                    util.Wrapper<util.color> attenuation,
                                    util.Wrapper<util.ray> scattered)
    {
        return false;
    }

}
