const { getDefaultConfig } = require('metro-config');

module.exports = (async () => {
  const config = await getDefaultConfig();

  return {
    resolver: {
      ...config.resolver,
      assetExts: config.resolver.assetExts,
      sourceExts: config.resolver.sourceExts
    }
  };
})();
