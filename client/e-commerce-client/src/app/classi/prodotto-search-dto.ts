import { LoginResponseDto } from './login-response-dto';


export class ProdottoSearchDto extends LoginResponseDto{
  searchKey: string;
  numeroPagina: number;
}
