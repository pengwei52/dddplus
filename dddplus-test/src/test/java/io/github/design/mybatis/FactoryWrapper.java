package io.github.design.mybatis;

import io.github.dddplus.mybatis.AutowireObjectFactory;
import org.springframework.stereotype.Component;

// 为了只扫描io.github.design package
@Component
public class FactoryWrapper extends AutowireObjectFactory {
}
