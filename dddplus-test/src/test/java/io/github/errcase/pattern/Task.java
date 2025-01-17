package io.github.errcase.pattern;

import io.github.dddplus.model.IDomainModel;
import io.github.dddplus.model.IIdentity;
import lombok.Getter;
import lombok.Setter;

public class Task implements IDomainModel, IIdentity {
    @Getter
    @Setter
    String taskType;
}
