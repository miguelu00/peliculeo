package controladores;

public abstract class UtilesBD {

    /**
     * Método para recoger el último ID de una tabla de la BBDD indicada por parámetro
     */
    public Object getLastIdFrom(String tableName) {
        String query = "SELECT MAX(ID) FROM " + tableName;
        return HibernateUtils.getCurrentSession().createNamedQuery(query, Object.class);
    }
}
