package org.example;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.accumulators.IntCounter;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * 使用 累加器计算 word_count 出现的单词数
 */
public class CustomAccumulator {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 从端口接入数据
        DataStreamSource<String> line = env.socketTextStream("localhost", 8888);

        line.keyBy(e -> e.split(",")[0])
                .process(
                        new KeyedProcessFunction<String, String, Tuple2<String, Integer>>() {

                            private ValueState<Integer> valueState;

                            // 创建一个累加器对象
                            private IntCounter wordCount = new IntCounter();

                            @Override
                            public void open(Configuration parameters) throws Exception {
                                // 使用 ValueState
                                ValueStateDescriptor<Integer> stateDescriptor = new ValueStateDescriptor<>("count_state", Integer.class);

                                // Managed Keyed State 方式 所有类型的状态均可以使用
                                stateDescriptor.setQueryable("query_count");
                                valueState = getRuntimeContext().getState(stateDescriptor);

                                // 注册累加器对象
                                getRuntimeContext().addAccumulator("word_count", wordCount);

                            }

                            @Override
                            public void processElement(String line, KeyedProcessFunction<String, String, Tuple2<String, Integer>>.Context ctx, Collector<Tuple2<String, Integer>> out) throws Exception {
                                String[] fields = line.split(",");
                                String name = fields[0];
                                Integer count = Integer.valueOf(fields[1]);

                                //每处理一个元素，累加器+1
                                wordCount.add(1);

                                //统计相同单词的值的和
                                Integer oldResult = valueState.value();
                                if (oldResult == null) {
                                    oldResult = 0;
                                }

                                count = oldResult + count;
                                valueState.update(count);

                                // 通过运行时上下文获取累加器结果
                                System.out.println("累加器结果-每个单词出现的数量：" + getRuntimeContext().getAccumulator("word_count"));
                                out.collect(new Tuple2<>(name, count));
                            }
                        }
                ).print();

        JobExecutionResult result = env.execute();
        Integer num = result.getAccumulatorResult("word_count");

        // 通过 JobExecutionResult 获取累加器执行结果
        System.out.println("累加器结果-出现的单词数量：" + num);
    }
}
