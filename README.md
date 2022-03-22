## APT(Annotation Processing Tool)注解处理器

### 项目简介
仿 ButterKnife 写的一个注解处理器，在编译期生成 XXBinding.java 文件，省去了 View 的 findViewById() 方法

### 项目说明
lib-annotations: 编写一个 BindView 注解
lib-processor: 根据注解生成为使用 BindView 注解的类生成对应的 XXBinding.java 文件
lib-bindview: 根据反射调用生成的 XXBinding.java 文件

### 使用说明
见 app 模块