package com.cnooc.lca;

import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Modules(scanPackage=true)
@Ok("json")
@Fail("json")
@IocBy(type = ComboIocProvider.class, args = {	//配置Ioc容器
		"*org.nutz.ioc.loader.json.JsonLoader", "ioc/", //扫描ioc文件夹中的js文件,作为JsonLoader的配置文件
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader","com.cnooc.lca"} 
		)
@SetupBy(value = LcaSetup.class)
public class MainModule {

}
