package service.engine;

import domain.EngineResult;
import domain.TreeRich;

import java.util.Map;

public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, final Map<String, String> decisionMatter);
}
