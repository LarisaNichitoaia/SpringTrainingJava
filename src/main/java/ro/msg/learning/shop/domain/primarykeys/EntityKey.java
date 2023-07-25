package ro.msg.learning.shop.domain.primarykeys;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter
@NoArgsConstructor
@Setter
public abstract class EntityKey {
    @Id
    @GeneratedValue
    protected UUID id;
}
