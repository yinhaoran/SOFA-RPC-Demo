// Generated by the ProtocolType buffer compiler.  DO NOT EDIT!
// source: ProtoService.proto

package com.example.sofa.rpc.protobuf;

public final class ProtoServiceModels {
	private ProtoServiceModels() {
	}

	public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
	}

	public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
		registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
	}

	static final com.google.protobuf.Descriptors.Descriptor internal_static_com_example_sofa_rpc_protobuf_EchoRequest_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_com_example_sofa_rpc_protobuf_EchoRequest_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_com_example_sofa_rpc_protobuf_EchoResponse_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_com_example_sofa_rpc_protobuf_EchoResponse_fieldAccessorTable;

	public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
		return descriptor;
	}

	private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
	static {
		java.lang.String[] descriptorData = { "\n\022ProtoService.proto\022\035com.example.sofa.r"
				+ "pc.protobuf\"P\n\013EchoRequest\022\014\n\004name\030\001 \001(\t"
				+ "\0223\n\005group\030\002 \001(\0162$.com.example.sofa.rpc.p"
				+ "rotobuf.Group\"-\n\014EchoResponse\022\014\n\004code\030\001 "
				+ "\001(\005\022\017\n\007message\030\002 \001(\t*\025\n\005Group\022\005\n\001A\020\000\022\005\n\001"
				+ "B\020\0012t\n\014ProtoService\022d\n\007echoObj\022*.com.exa"
				+ "mple.sofa.rpc.protobuf.EchoRequest\032+.com" + ".example.sofa.rpc.protobuf.EchoResponse\""
				+ "\000B\026B\022ProtoServiceModelsP\001b\006proto3" };
		com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
			@Override
			public com.google.protobuf.ExtensionRegistry assignDescriptors(
					com.google.protobuf.Descriptors.FileDescriptor root) {
				descriptor = root;
				return null;
			}
		};
		com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData,
				new com.google.protobuf.Descriptors.FileDescriptor[] {}, assigner);
		internal_static_com_example_sofa_rpc_protobuf_EchoRequest_descriptor = getDescriptor().getMessageTypes().get(0);
		internal_static_com_example_sofa_rpc_protobuf_EchoRequest_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_com_example_sofa_rpc_protobuf_EchoRequest_descriptor,
				new java.lang.String[] { "Name", "Group", });
		internal_static_com_example_sofa_rpc_protobuf_EchoResponse_descriptor = getDescriptor().getMessageTypes()
				.get(1);
		internal_static_com_example_sofa_rpc_protobuf_EchoResponse_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_com_example_sofa_rpc_protobuf_EchoResponse_descriptor,
				new java.lang.String[] { "Code", "Message", });
	}

	// @@protoc_insertion_point(outer_class_scope)
}
