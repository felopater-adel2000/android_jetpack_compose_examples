import fs from 'fs';

// Load original JSON
const json = JSON.parse(fs.readFileSync('./Foundations.json', 'utf-8'));

// Recursively fix references and fill missing values
/**
 *
 * @param {JSON} obj
 */
function fixRefsAndFillDefaults(obj) {
  for (const key in obj) {
    if (obj[key] && typeof obj[key] === 'object') {
      // Recursively go deeper first
      fixRefsAndFillDefaults(obj[key]);

      // Fix references in string values
      if (typeof obj[key].value === 'string') {
        obj[key].value = obj[key].value
          .replace(/\{colors\./g, '{global/default.colors.')
          .replace(/\{scale\./g, '{global/default.scale.')
          .replace(/ /g, '_');
      }

      // Fill missing values based on type
      if ('type' in obj[key] && !('value' in obj[key])) {
        if (obj[key].type === 'number') obj[key].value = 0;
        if (obj[key].type === 'color') obj[key].value = '#000000';
      }
    }
  }
}

fixRefsAndFillDefaults(json);

export default json;