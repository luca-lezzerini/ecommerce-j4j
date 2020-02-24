import { LoginResponseDto } from './login-response-dto';
import { Ordine } from './ordine';

export class OrdineSearchResultsDto extends LoginResponseDto {
  results: Ordine[];
  page: number;
  ultimaPagina: boolean;
}
