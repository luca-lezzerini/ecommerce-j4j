import { Taglia } from './taglia';

export class TagliaSearchResultsDto {
  result: Taglia[] = [];
  page: number;
  first: boolean;
  last: boolean;
}
