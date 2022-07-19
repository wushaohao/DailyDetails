MultiSet: 无序+可重复 count()方法获取单词的次数 增强了可读性+操作简单
创建方式: Multiset<String> set = HashMultiset.create();
Multimap: key-value key可以重复
创建方式: Multimap<String, String> teachers = ArrayListMultimap.create();
BiMap: 双向Map(Bidirectional Map) 键与值都不能重复
创建方式: BiMap<String, String> biMap = HashBiMap.create();
Table: 双键的Map Map--> Table-->rowKey+columnKey+value //和sql中的联合主键有点像
创建方式: Table<String, String, Integer> tables = HashBasedTable.create();