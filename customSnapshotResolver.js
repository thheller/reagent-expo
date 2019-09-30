module.exports = {
  resolveSnapshotPath: (testPath, snapshotExtension) =>
    testPath.replace('test-out', 'src/test/snapshots') + snapshotExtension,

  resolveTestPath: (snapshotFilePath, snapshotExtension) =>
    snapshotFilePath
    .replace('src/test/snapshots', 'test-out')
    .slice(0, -snapshotExtension.length),

  testPathForConsistencyCheck: 'example.test.js'
};
