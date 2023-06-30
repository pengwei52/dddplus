/*
 * Copyright DDDplus Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
/**
 * Reverse modeling DSL: Object-Oriented Domain Model(As-Is -> To-Be) Re-Design without tech constraints.
 * <p/>
 * <p>目标：提供一套模型描述语言，从代码提炼精粹的业务知识，指导模型和系统演进.</p>
 * <p>路径：明确业务中关键问题，跨越(架构约束，技术限制)等造成代码难以直观表达模型的问题，面向对象地抽取和修正，形成与正向模型正反馈闭环。DSL标注过程，是大胆二次设计的过程.</p>
 * <p/>
 * <p>由于代码具有(可运行，包含完全细节，演进过程完整追溯，自我修复)特征，因此成为业务的唯一事实真相；但代码里有太多技术细节产生的业务模型噪音，导致代码里无法直观看到业务真相.</p>
 * <p>{@code 建模 = 图形 + 逻辑 + 现实的抽象}，代码(一维的，局部的)，而模型(多维立体的，全局的)，逆向模型相当于动态的活地图</p>
 * <p>通过该DSL建立的逆向模型，不仅仅是业务概念模型，更是一线研发可落地的系统模型，支持(正/逆)向循环迭代、交叉验证，最终实现(业务模型，代码)的一致.</p>
 * <pre>
 * DomainModel      -->   CodeImplementation
 *     ^                        |
 *     |                        V
 * KnowledgeCrunch  <--   ReversedDomainModel
 * </pre>
 * <p/>
 * <ul>DSL能力：
 * <li>字段级：
 * <ul>
 *     <li>{@link io.github.dddplus.dsl.KeyElement}</li>
 * </ul>
 * </li>
 * <li>方法级：
 * <ul>
 *     <li>{@link io.github.dddplus.dsl.KeyRule}</li>
 *     <li>{@link io.github.dddplus.dsl.KeyBehavior}</li>
 *     <li>{@link io.github.dddplus.dsl.KeyFlow}</li>
 *     <li>{@link io.github.dddplus.dsl.KeyUsecase}</li>
 * </ul>
 * </li>
 * <li>类级：
 * <ul>
 *     <li>{@link io.github.dddplus.dsl.KeyRelation}</li>
 *     <li>{@link io.github.dddplus.dsl.KeyEvent}</li>
 *     <li>{@link io.github.dddplus.dsl.IVirtualModel}</li>
 * </ul>
 * </li>
 * <li>包级：
 * <ul>
 *     <li>{@link io.github.dddplus.dsl.Aggregate}</li>
 * </ul>
 * </li>
 * </ul>
 * <ul>DSL提供的代码模型修正机制：
 * <li>{@link io.github.dddplus.dsl.KeyElement#name()}，{@link io.github.dddplus.dsl.KeyBehavior#name()}等，修正关键概念名称</li>
 * <li>{@link io.github.dddplus.dsl.IVirtualModel}，识别关键职责对象</li>
 * <li>{@link io.github.dddplus.dsl.KeyFlow#actor()}，重新分配行为职责</li>
 * </ul>
 * <p/>
 * <ul>逆向建模指导原则：
 * <li>知识完整</li>
 * <li>最少元素</li>
 * <li>揭示意图</li>
 * <li>消除噪音</li>
 * </ul>
 * <ul>如何评估逆向模型质量？
 * <li>模型是否直观完整表达出了业务核心知识</li>
 * <li>产品、业务方是否能看懂</li>
 * <li>日常开发，是否{@code 从模型中来，到模型中去}闭环</li>
 * </ul>
 * <ul>如何使用模型：
 * <li>围绕模型，建立有效的业务知识沟通和反馈机制</li>
 * <li>业务需求 -> 模型修改 -> 设计评审 -> 代码实现(依据活地图) -> 自动生成逆向模型，验证设计与实现一致性</li>
 * <li>围绕模型，构造易于维护的软件
 *   <ul>理解了模型
 *      <li>就大致理解了代码结构</li>
 *      <li>讨论需求时，研发就能容易明白需要改动的代码，评估工期和风险</li>
 *      <li>便于知识传递，在业务中台下便于BP接受</li>
 *   </ul>
 * </li>
 * </ul>
 *
 * @see <a href="https://xie.infoq.cn/article/3da89918c7d27ccc8e8f98ab7">面向对象设计的逆向建模方法和开源工具</a>
 * @see <a href="https://ieeexplore.ieee.org/document/723185/">Requirements for integrating software architecture and reengineering models</a>
 * @see <a href="http://www.jos.org.cn/jos/article/pdf/6278">面向领域驱动设计的逆向建模支持方法</a>
 * @see <a href="https://www.eclipse.org/MoDisco/">MoDisco</a>
 */
package io.github.dddplus.dsl;