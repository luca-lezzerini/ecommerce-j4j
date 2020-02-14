import { LoginResponseDto } from './login-response-dto';
import { Prodotto } from './prodotto';


export class ProdottoSearchResultsDto extends LoginResponseDto{
  results: Prodotto[];
}
