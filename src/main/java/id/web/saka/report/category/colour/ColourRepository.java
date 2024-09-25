package id.web.saka.report.category.colour;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ColourRepository extends JpaRepository<Colour, Integer> {
    @Query("SELECT s FROM Colour s WHERE s.name = :name ")
    Colour findByColourName(@Param("name") String name);

}
