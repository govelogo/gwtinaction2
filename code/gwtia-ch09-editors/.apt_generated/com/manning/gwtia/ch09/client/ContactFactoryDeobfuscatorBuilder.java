// Automatically Generated -- DO NOT EDIT
// com.manning.gwtia.ch09.client.ContactFactory
package com.manning.gwtia.ch09.client;
import java.util.Arrays;
import com.google.web.bindery.requestfactory.vm.impl.OperationData;
import com.google.web.bindery.requestfactory.vm.impl.OperationKey;
public final class ContactFactoryDeobfuscatorBuilder extends com.google.web.bindery.requestfactory.vm.impl.Deobfuscator.Builder {
{
withOperation(new OperationKey("GGl81Vy_vNVzYeym6yc3IzSXY4o="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/lang/Integer;")
  .withMethodName("count")
  .withRequestContext("com.manning.gwtia.ch09.client.ContactFactory$ContactRequest")
  .build());
withOperation(new OperationKey("n4$hvY7G9rvMvwaknw05iiV3_Cg="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/manning/gwtia/ch09/client/ContactProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/manning/gwtia/ch09/server/Contact;)V")
  .withMethodName("remove")
  .withRequestContext("com.manning.gwtia.ch09.client.ContactFactory$ContactRequest")
  .build());
withOperation(new OperationKey("QwDj3Y97ygndf7YPsEV$PWsLMcY="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/manning/gwtia/ch09/client/ContactProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/manning/gwtia/ch09/server/Contact;)Lcom/manning/gwtia/ch09/server/Contact;")
  .withMethodName("persist")
  .withRequestContext("com.manning.gwtia.ch09.client.ContactFactory$ContactRequest")
  .build());
withOperation(new OperationKey("s1kZyxxXL7BKrl7YWcsg2gZaK8s="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("findAllContacts")
  .withRequestContext("com.manning.gwtia.ch09.client.ContactFactory$ContactRequest")
  .build());
withOperation(new OperationKey("hbuXYnqDxRii$XiEmAo25qijJx0="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/Long;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/Long;)Lcom/manning/gwtia/ch09/server/Contact;")
  .withMethodName("find")
  .withRequestContext("com.manning.gwtia.ch09.client.ContactFactory$ContactRequest")
  .build());
withOperation(new OperationKey("c2l$KftM5FgCyocImGHJH$R_uRs="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/Long;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/Long;)Ljava/util/List;")
  .withMethodName("phoneList")
  .withRequestContext("com.manning.gwtia.ch09.client.ContactFactory$PhoneRequest")
  .build());
withOperation(new OperationKey("CVkO9G3a__OLi_z89Zq$fqqO6mI="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/InstanceRequest;")
  .withDomainMethodDescriptor("()V")
  .withMethodName("persist")
  .withRequestContext("com.manning.gwtia.ch09.client.ContactFactory$PhoneRequest")
  .build());
withRawTypeToken("w1Qg$YHpDaNcHrR5HZ$23y518nA=", "com.google.web.bindery.requestfactory.shared.EntityProxy");
withRawTypeToken("FXHD5YU0TiUl3uBaepdkYaowx9k=", "com.google.web.bindery.requestfactory.shared.BaseProxy");
withRawTypeToken("d0Jq7UI9hO1eQcJqaaTaqXhX$eQ=", "com.manning.gwtia.ch09.client.ContactProxy");
withRawTypeToken("yqrVJlJiYVJ1flu2YX_Zuftshwg=", "com.manning.gwtia.ch09.client.PhoneProxy");
withClientToDomainMappings("com.manning.gwtia.ch09.server.Contact", Arrays.asList("com.manning.gwtia.ch09.client.ContactProxy"));
withClientToDomainMappings("com.manning.gwtia.ch09.server.Contact$Phone", Arrays.asList("com.manning.gwtia.ch09.client.PhoneProxy"));
}}
