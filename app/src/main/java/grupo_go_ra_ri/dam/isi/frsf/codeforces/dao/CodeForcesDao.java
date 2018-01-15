package grupo_go_ra_ri.dam.isi.frsf.codeforces.dao;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;

/**
 * Created by hrickert on 14/01/2018.
 */

public interface CodeForcesDao {
    /* TODO Seguir completando con demás métodos (obtener Competencias, etc) */
    public User getUserByHandle(String handle);

    public void create(User u);
    public void update(User u);
    public void delete(User u);
}
