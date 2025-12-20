import { BLOCKED_DOMAINS } from './BlockedDomains';

export function isBlockedDomain(urlOrText) {
  if (!urlOrText || typeof urlOrText !== 'string') return false;

  const lower = urlOrText.toLowerCase();

  return BLOCKED_DOMAINS.some(domain =>
    lower.includes(domain)
  );
}
