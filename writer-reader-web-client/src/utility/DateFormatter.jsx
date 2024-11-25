export const DateFormatter = (dateStr) => {
  const date = new Date(dateStr);
  return date.toLocaleDateString("hu-HU");
};
