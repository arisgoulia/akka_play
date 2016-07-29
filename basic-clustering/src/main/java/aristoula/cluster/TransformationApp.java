package aristoula.cluster;

public class TransformationApp {

  public static void main(String[] args) {
    // starting 2 frontend nodes and 3 backend nodes
    TransformationBackendMain.main(new String[] { "2553" });
    TransformationBackendMain.main(new String[] { "2554" });
    TransformationBackendMain.main(new String[0]);
    TransformationFrontendMain.main(new String[0]);
  }
}
