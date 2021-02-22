package com.niubaide.im.conf;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;


@Configuration
@Import(FdfsClientConfig.class)
/**
 * 解决jmx重复注册bean的问题
 */
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
/**
 * 导入FastDFS-Client组件
 *
 * @author Jax
 * @author tobato
 *
 */
public class ComponetImport {
    // 导入依赖组件
}
