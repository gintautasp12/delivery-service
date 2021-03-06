package lt.vu.persistence.repository;

import lt.vu.persistence.entities.PackageSize;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class PackageSizeRepository {

    @Inject
    private EntityManager entityManager;

    public List<PackageSize> findAll() {
        return this.entityManager.createNamedQuery("PackageSize.findAll", PackageSize.class).getResultList();
    }
}
